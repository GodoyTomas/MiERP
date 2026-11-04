package com.godoytomas.mierp.modules.purchases;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.Empresa;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class ComprasService {

    private final OrdenCompraRepository ordenCompraRepository;
    private final ProveedorRepository proveedorRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ComprasService(OrdenCompraRepository ordenCompraRepository,
                          ProveedorRepository proveedorRepository,
                          ApplicationEventPublisher eventPublisher) {
        this.ordenCompraRepository = ordenCompraRepository;
        this.proveedorRepository = proveedorRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public OrdenCompra emitirOrdenCompra(OrdenCompra orden) {
        BigDecimal total = BigDecimal.ZERO;

        for (DetalleOrdenCompra item : orden.getItems()) {
            item.setOrdenCompra(orden);
            BigDecimal subtotal = item.getCostoUnitarioPactado()
                    .multiply(item.getCantidadPedida())
                    .setScale(2, RoundingMode.HALF_UP);
            item.setSubtotal(subtotal);
            total = total.add(subtotal);
        }

        orden.setTotalEstimado(total);
        orden.setEstado(OrdenCompra.EstadoOrden.APROBADA);
        return ordenCompraRepository.save(orden);
    }

    // Módulo 15 y 17: Control de recepción de mercadería e impacto en deuda y stock
    @Transactional
    public OrdenCompra registrarRecepcionMercaderia(Long ordenId, String observacionesCalidad) {
        OrdenCompra orden = ordenCompraRepository.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden de compra no encontrada: " + ordenId));

        if (orden.getEstado() == OrdenCompra.EstadoOrden.RECIBIDA_COMPLETA) {
            throw new IllegalStateException("Esta orden de compra ya fue recibida anteriormente.");
        }

        for (DetalleOrdenCompra item : orden.getItems()) {
            if (item.getCantidadRecibida().compareTo(BigDecimal.ZERO) == 0) {
                item.setCantidadRecibida(item.getCantidadPedida());
            }
        }

        orden.setEstado(OrdenCompra.EstadoOrden.RECIBIDA_COMPLETA);
        orden.setObservacionesRecepcion(observacionesCalidad);
        orden.setFechaRecepcion(LocalDateTime.now());

        // Módulo 15: Sumar deuda en la cuenta corriente del proveedor
        Empresa empresa = orden.getEmpresa();
        if (empresa.getModulosActivos().contains(CodigoModulo.M15_PROVEEDORES_CTA_CTE)) {
            Proveedor proveedor = orden.getProveedor();
            proveedor.setSaldoDeuda(proveedor.getSaldoDeuda().add(orden.getTotalEstimado()));
            proveedorRepository.save(proveedor);
        }

        OrdenCompra guardada = ordenCompraRepository.save(orden);

        // Disparar evento para que el módulo de Inventario sume el stock y actualice el costo
        eventPublisher.publishEvent(new MercaderiaRecibidaEvent(guardada));

        return guardada;
    }
}
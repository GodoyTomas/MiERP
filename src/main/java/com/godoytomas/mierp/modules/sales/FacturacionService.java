package com.godoytomas.mierp.modules.sales;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.Empresa;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FacturacionService {

    private final ComprobanteVentaRepository comprobanteRepository;
    private final ClienteRepository clienteRepository;
    private final ApplicationEventPublisher eventPublisher;

    public FacturacionService(ComprobanteVentaRepository comprobanteRepository,
                              ClienteRepository clienteRepository,
                              ApplicationEventPublisher eventPublisher) {
        this.comprobanteRepository = comprobanteRepository;
        this.clienteRepository = clienteRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public ComprobanteVenta emitirComprobante(ComprobanteVenta comprobante, Long depositoId, boolean enCuentaCorriente) {
        Empresa empresa = comprobante.getEmpresa();
        BigDecimal netoAcumulado = BigDecimal.ZERO;
        BigDecimal ivaAcumulado = BigDecimal.ZERO;

        // 1. Calcular importes renglón por renglón e IVA según alícuota del producto
        for (DetalleComprobanteVenta item : comprobante.getItems()) {
            item.setComprobante(comprobante);

            BigDecimal bruto = item.getPrecioUnitario().multiply(item.getCantidad());
            BigDecimal factorDescuento = BigDecimal.ONE.subtract(
                    item.getPorcentajeDescuento().divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP)
            );
            BigDecimal netoLinea = bruto.multiply(factorDescuento).setScale(2, RoundingMode.HALF_UP);
            item.setSubtotalLinea(netoLinea);

            BigDecimal alicuotaIva = item.getProducto().getAlicuotaIva();
            BigDecimal ivaLinea = netoLinea.multiply(alicuotaIva)
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

            netoAcumulado = netoAcumulado.add(netoLinea);
            ivaAcumulado = ivaAcumulado.add(ivaLinea);
        }

        comprobante.setSubtotalNeto(netoAcumulado);
        comprobante.setTotalIva(ivaAcumulado);
        comprobante.setTotalFinal(netoAcumulado.add(ivaAcumulado));

        // 2. Módulo 8: Actualizar Cuenta Corriente y verificar límite de crédito si está activo
        if (enCuentaCorriente && empresa.getModulosActivos().contains(CodigoModulo.M08_CLIENTES_CTA_CTE)) {
            Cliente cliente = comprobante.getCliente();
            BigDecimal nuevoSaldo = cliente.getSaldoCuentaCorriente().add(comprobante.getTotalFinal());

            if (cliente.getLimiteCredito().compareTo(BigDecimal.ZERO) > 0 &&
                    nuevoSaldo.compareTo(cliente.getLimiteCredito()) > 0) {
                throw new IllegalStateException("Operación rechazada: El cliente supera su límite de crédito autorizado.");
            }
            cliente.setSaldoCuentaCorriente(nuevoSaldo);
            clienteRepository.save(cliente);
        }

        // 3. Módulo 9: Autorización Electrónica ARCA (Generación de CAE de 14 dígitos para Facturas)
        if (esComprobanteFiscal(comprobante.getTipo()) &&
                empresa.getModulosActivos().contains(CodigoModulo.M09_FACTURACION_ARCA)) {
            String caeGenerado = String.valueOf(ThreadLocalRandom.current().nextLong(70000000000000L, 79999999999999L));
            comprobante.setCaeArca(caeGenerado);
            comprobante.setVencimientoCaeArca(LocalDate.now().plusDays(10));
        }

        ComprobanteVenta guardado = comprobanteRepository.save(comprobante);

        // 4. Emitir evento para que Inventario (y Contabilidad) actúen si la empresa los tiene activos
        eventPublisher.publishEvent(new VentaConfirmadaEvent(guardado, depositoId));

        return guardado;
    }

    private boolean esComprobanteFiscal(ComprobanteVenta.TipoComprobante tipo) {
        return tipo == ComprobanteVenta.TipoComprobante.FACTURA_A ||
                tipo == ComprobanteVenta.TipoComprobante.FACTURA_B ||
                tipo == ComprobanteVenta.TipoComprobante.FACTURA_C ||
                tipo == ComprobanteVenta.TipoComprobante.NOTA_CREDITO;
    }
}
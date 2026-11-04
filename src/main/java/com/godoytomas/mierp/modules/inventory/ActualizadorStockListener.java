package com.godoytomas.mierp.modules.inventory;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.modules.purchases.DetalleOrdenCompra;
import com.godoytomas.mierp.modules.purchases.MercaderiaRecibidaEvent;
import com.godoytomas.mierp.modules.purchases.OrdenCompra;
import com.godoytomas.mierp.modules.sales.ComprobanteVenta;
import com.godoytomas.mierp.modules.sales.DetalleComprobanteVenta;
import com.godoytomas.mierp.modules.sales.VentaConfirmadaEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ActualizadorStockListener {

    private final ExistenciaStockRepository existenciaRepository;
    private final MovimientoStockRepository movimientoRepository;
    private final ProductoRepository productoRepository;

    public ActualizadorStockListener(ExistenciaStockRepository existenciaRepository,
                                     MovimientoStockRepository movimientoRepository,
                                     ProductoRepository productoRepository) {
        this.existenciaRepository = existenciaRepository;
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    // 1. Escucha cuando se emite una Venta -> Descuenta Stock
    @EventListener
    public void alConfirmarVenta(VentaConfirmadaEvent event) {
        ComprobanteVenta comprobante = event.getComprobante();
        Empresa empresa = comprobante.getEmpresa();

        if (!empresa.getModulosActivos().contains(CodigoModulo.M18_INVENTARIO_STOCK) ||
                event.getDepositoOrigenId() == null ||
                comprobante.getTipo() == ComprobanteVenta.TipoComprobante.PRESUPUESTO) {
            return;
        }

        for (DetalleComprobanteVenta item : comprobante.getItems()) {
            if (item.getProducto().getTipo() == Producto.TipoProducto.SERVICIO) {
                continue;
            }

            ExistenciaStock existencia = existenciaRepository
                    .findByProductoIdAndDepositoId(item.getProducto().getId(), event.getDepositoOrigenId())
                    .orElseThrow(() -> new IllegalStateException(
                            "No existe stock inicializado en el depósito para el producto: " + item.getProducto().getNombre()
                    ));

            BigDecimal nuevoSaldo = existencia.getCantidadDisponible().subtract(item.getCantidad());
            existencia.setCantidadDisponible(nuevoSaldo);
            existenciaRepository.save(existencia);

            MovimientoStock mov = MovimientoStock.builder()
                    .empresa(empresa)
                    .producto(item.getProducto())
                    .deposito(existencia.getDeposito())
                    .tipo(MovimientoStock.TipoMovimiento.EGRESO_VENTA)
                    .cantidad(item.getCantidad().negate())
                    .saldoResultante(nuevoSaldo)
                    .comprobanteReferencia(comprobante.getTipo() + "-" + comprobante.getPuntoVenta() + "-" + comprobante.getNumeroComprobante())
                    .motivoObservacion("Descuento automático por venta")
                    .usuarioResponsable("SISTEMA")
                    .build();

            movimientoRepository.save(mov);
        }
    }

    // 2. Escucha cuando se recibe una Orden de Compra -> Suma Stock y actualiza Costo
    @EventListener
    public void alRecibirMercaderiaCompra(MercaderiaRecibidaEvent event) {
        OrdenCompra orden = event.getOrdenCompra();
        Empresa empresa = orden.getEmpresa();

        if (!empresa.getModulosActivos().contains(CodigoModulo.M18_INVENTARIO_STOCK) ||
                orden.getDepositoDestino() == null) {
            return;
        }

        for (DetalleOrdenCompra item : orden.getItems()) {
            Producto producto = item.getProducto();
            if (producto.getTipo() == Producto.TipoProducto.SERVICIO) {
                continue;
            }

            // Actualizar costo de reposición con el último precio comprado
            producto.setCostoReposicion(item.getCostoUnitarioPactado());
            productoRepository.save(producto);

            // Buscar existencia en el depósito o crearla si es la primera vez que ingresa
            ExistenciaStock existencia = existenciaRepository
                    .findByProductoIdAndDepositoId(producto.getId(), orden.getDepositoDestino().getId())
                    .orElseGet(() -> ExistenciaStock.builder()
                            .producto(producto)
                            .deposito(orden.getDepositoDestino())
                            .cantidadDisponible(BigDecimal.ZERO)
                            .cantidadReservada(BigDecimal.ZERO)
                            .build());

            BigDecimal nuevoSaldo = existencia.getCantidadDisponible().add(item.getCantidadRecibida());
            existencia.setCantidadDisponible(nuevoSaldo);
            existenciaRepository.save(existencia);

            MovimientoStock mov = MovimientoStock.builder()
                    .empresa(empresa)
                    .producto(producto)
                    .deposito(orden.getDepositoDestino())
                    .tipo(MovimientoStock.TipoMovimiento.INGRESO_COMPRA)
                    .cantidad(item.getCantidadRecibida())
                    .saldoResultante(nuevoSaldo)
                    .comprobanteReferencia(orden.getNumeroOrden())
                    .motivoObservacion("Ingreso por recepción de Orden de Compra")
                    .usuarioResponsable("SISTEMA")
                    .build();

            movimientoRepository.save(mov);
        }
    }
}
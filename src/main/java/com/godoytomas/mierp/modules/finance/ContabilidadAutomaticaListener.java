package com.godoytomas.mierp.modules.finance;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.modules.purchases.MercaderiaRecibidaEvent;
import com.godoytomas.mierp.modules.purchases.OrdenCompra;
import com.godoytomas.mierp.modules.sales.ComprobanteVenta;
import com.godoytomas.mierp.modules.sales.VentaConfirmadaEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContabilidadAutomaticaListener {

    private final ContabilidadService contabilidadService;

    public ContabilidadAutomaticaListener(ContabilidadService contabilidadService) {
        this.contabilidadService = contabilidadService;
    }

    // 1. Si la empresa tiene Contabilidad General activa, contabiliza cada Factura de Venta
    @EventListener
    public void contabilizarVenta(VentaConfirmadaEvent event) {
        ComprobanteVenta comp = event.getComprobante();
        Empresa empresa = comp.getEmpresa();

        if (!empresa.getModulosActivos().contains(CodigoModulo.M26_CONTABILIDAD_GENERAL) ||
                comp.getTipo() == ComprobanteVenta.TipoComprobante.PRESUPUESTO ||
                comp.getTipo() == ComprobanteVenta.TipoComprobante.PEDIDO) {
            return;
        }

        CuentaContable ctaDeudores = contabilidadService.obtenerOCrearCuenta(
                empresa, "1.1.02.001", "Deudores por Ventas", CuentaContable.TipoCuenta.ACTIVO);
        CuentaContable ctaVentas = contabilidadService.obtenerOCrearCuenta(
                empresa, "4.1.01.001", "Ventas de Mercaderías y Servicios", CuentaContable.TipoCuenta.INGRESO_RESULTADO_POSITIVO);
        CuentaContable ctaIvaDebito = contabilidadService.obtenerOCrearCuenta(
                empresa, "2.1.03.001", "IVA Débito Fiscal (ARCA)", CuentaContable.TipoCuenta.PASIVO);

        List<RenglonAsiento> renglones = new ArrayList<>();

        // DEBE: Entra el derecho a cobrar por el total final
        renglones.add(RenglonAsiento.builder()
                .cuenta(ctaDeudores)
                .debe(comp.getTotalFinal())
                .haber(BigDecimal.ZERO)
                .leyenda("Deudor por venta - Cliente: " + comp.getCliente().getRazonSocialNombre())
                .build());

        // HABER: Venta neta
        renglones.add(RenglonAsiento.builder()
                .cuenta(ctaVentas)
                .debe(BigDecimal.ZERO)
                .haber(comp.getSubtotalNeto())
                .leyenda("Ingreso neto por venta")
                .build());

        // HABER: IVA Débito Fiscal (si corresponde)
        if (comp.getTotalIva().compareTo(BigDecimal.ZERO) > 0) {
            renglones.add(RenglonAsiento.builder()
                    .cuenta(ctaIvaDebito)
                    .debe(BigDecimal.ZERO)
                    .haber(comp.getTotalIva())
                    .leyenda("IVA Débito Fiscal ARCA")
                    .build());
        }

        AsientoContable asiento = AsientoContable.builder()
                .empresa(empresa)
                .fechaContable(LocalDate.now())
                .concepto("Asiento automático Venta " + comp.getTipo() + " " + comp.getPuntoVenta() + "-" + comp.getNumeroComprobante())
                .comprobanteOrigen(comp.getTipo() + "-" + comp.getNumeroComprobante())
                .automatico(true)
                .renglones(renglones)
                .build();

        contabilidadService.registrarAsiento(asiento);
    }

    // 2. Si la empresa tiene Contabilidad General activa, contabiliza cada Recepción de Compra
    @EventListener
    public void contabilizarCompra(MercaderiaRecibidaEvent event) {
        OrdenCompra orden = event.getOrdenCompra();
        Empresa empresa = orden.getEmpresa();

        if (!empresa.getModulosActivos().contains(CodigoModulo.M26_CONTABILIDAD_GENERAL)) {
            return;
        }

        CuentaContable ctaMercaderias = contabilidadService.obtenerOCrearCuenta(
                empresa, "1.1.04.001", "Mercaderías de Reventa / Insumos", CuentaContable.TipoCuenta.ACTIVO);
        CuentaContable ctaProveedores = contabilidadService.obtenerOCrearCuenta(
                empresa, "2.1.01.001", "Proveedores Locales", CuentaContable.TipoCuenta.PASIVO);

        List<RenglonAsiento> renglones = List.of(
                RenglonAsiento.builder()
                        .cuenta(ctaMercaderias)
                        .debe(orden.getTotalEstimado())
                        .haber(BigDecimal.ZERO)
                        .leyenda("Ingreso de stock OC " + orden.getNumeroOrden())
                        .build(),
                RenglonAsiento.builder()
                        .cuenta(ctaProveedores)
                        .debe(BigDecimal.ZERO)
                        .haber(orden.getTotalEstimado())
                        .leyenda("Deuda con proveedor " + orden.getProveedor().getRazonSocial())
                        .build()
        );

        AsientoContable asiento = AsientoContable.builder()
                .empresa(empresa)
                .fechaContable(LocalDate.now())
                .concepto("Asiento automático Recepción Compra " + orden.getNumeroOrden())
                .comprobanteOrigen(orden.getNumeroOrden())
                .automatico(true)
                .renglones(new ArrayList<>(renglones))
                .build();

        contabilidadService.registrarAsiento(asiento);
    }
}
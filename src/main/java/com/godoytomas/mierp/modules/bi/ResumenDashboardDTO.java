package com.godoytomas.mierp.modules.bi;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ResumenDashboardDTO {
    private String razonSocialEmpresa;
    private String tamanoEmpresa;
    private int cantidadModulosActivos;
    private Set<String> modulosHabilitados;

    // Indicadores KPI Comerciales y Financieros (Módulo 49)
    private BigDecimal totalVentasFacturadas;
    private int cantidadFacturasEmitidas;
    private BigDecimal cuentasPorCobrarClientes;
    private BigDecimal deudaTotalProveedores;
    private BigDecimal resultadoOperativoEstimado;

    // Alertas operativas en tiempo real (Módulos 1 y 54)
    private int alertasProductosStockBajo;
    private List<String> detalleAlertasCriticas;

    // Pronóstico Financiero a 30 días (Módulo 50)
    private BigDecimal proyeccionVentasProximoMes;
}
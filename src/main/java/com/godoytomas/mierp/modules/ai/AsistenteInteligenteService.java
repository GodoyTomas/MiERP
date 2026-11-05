package com.godoytomas.mierp.modules.ai;

import com.godoytomas.mierp.modules.bi.BusinessIntelligenceService;
import com.godoytomas.mierp.modules.bi.ResumenDashboardDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsistenteInteligenteService {

    private final BusinessIntelligenceService biService;

    public AsistenteInteligenteService(BusinessIntelligenceService biService) {
        this.biService = biService;
    }

    public ConsultaAsistenteDTO.RespuestaIA procesarConsulta(Long empresaId, String pregunta) {
        ResumenDashboardDTO datos = biService.construirDashboard(empresaId);
        String consultaNorm = pregunta != null ? pregunta.toLowerCase() : "";

        StringBuilder analisis = new StringBuilder();
        List<String> recomendaciones = new ArrayList<>();

        analisis.append("Diagnóstico IA para ").append(datos.getRazonSocialEmpresa()).append(": ");

        if (consultaNorm.contains("venta") || consultaNorm.contains("factura") || consultaNorm.contains("ingreso")) {
            analisis.append("Actualmente registras ").append(datos.getCantidadFacturasEmitidas())
                    .append(" comprobantes emitidos por un total de $").append(datos.getTotalVentasFacturadas())
                    .append(". La proyección estimada para los próximos 30 días es de $")
                    .append(datos.getProyeccionVentasProximoMes()).append(".");
            recomendaciones.add("Activar promociones sobre listas de precios (Módulo 12) para acelerar la rotación.");
            recomendaciones.add("Gestionar la cobranza de $" + datos.getCuentasPorCobrarClientes() + " pendientes en cuentas corrientes de clientes.");
        } else if (consultaNorm.contains("stock") || consultaNorm.contains("inventario") || consultaNorm.contains("deposito")) {
            analisis.append("Se detectaron ").append(datos.getAlertasProductosStockBajo())
                    .append(" artículos en nivel crítico de reposición.");
            if (!datos.getDetalleAlertasCriticas().isEmpty()) {
                recomendaciones.addAll(datos.getDetalleAlertasCriticas());
            }
            recomendaciones.add("Emitir Órdenes de Compra automáticas (Módulo 13) para cubrir los faltantes.");
        } else {
            // Resumen integral de toma de decisiones
            analisis.append("La empresa opera con ").append(datos.getCantidadModulosActivos())
                    .append(" módulos activos sobre los 66 disponibles. ")
                    .append("Facturación acumulada: $").append(datos.getTotalVentasFacturadas())
                    .append(" | Cuentas a cobrar: $").append(datos.getCuentasPorCobrarClientes())
                    .append(" | Deuda con proveedores: $").append(datos.getDeudaTotalProveedores())
                    .append(" | Balance operativo: $").append(datos.getResultadoOperativoEstimado()).append(".");

            if (datos.getAlertasProductosStockBajo() > 0) {
                recomendaciones.add("Revisar urgente los " + datos.getAlertasProductosStockBajo() + " productos con stock bajo.");
            }
            recomendaciones.add("Utilizar la conciliación de Tesorería (Módulo 28) para calzar pagos a proveedores con cobranzas.");
        }

        return ConsultaAsistenteDTO.RespuestaIA.builder()
                .preguntaOriginal(pregunta)
                .analisisEjecutivo(analisis.toString())
                .accionesRecomendadas(recomendaciones)
                .fechaHora(LocalDateTime.now())
                .build();
    }
}
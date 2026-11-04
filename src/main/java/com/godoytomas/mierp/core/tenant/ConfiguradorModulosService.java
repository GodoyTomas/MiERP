package com.godoytomas.mierp.core.tenant;

import org.springframework.stereotype.Service;
import java.util.EnumSet;
import java.util.Set;
import static com.godoytomas.mierp.core.tenant.CodigoModulo.*;

@Service
public class ConfiguradorModulosService {

    /**
     * Evalúa las características cargadas por la empresa y activa automáticamente
     * los módulos correspondientes de los 66 disponibles.
     */
    public void autoconfigurarModulos(Empresa empresa) {
        // 1. Módulos Base (Siempre activos para cualquier empresa)
        Set<CodigoModulo> modulos = EnumSet.of(
                M01_DASHBOARD_PRINCIPAL, M02_EMPRESAS_SUCURSALES, M03_USUARIOS_PERMISOS,
                M04_CONFIGURACION_GENERAL, M05_GESTION_DOCUMENTAL,
                M06_VENTAS, M08_CLIENTES_CTA_CTE, M09_FACTURACION_ARCA, M12_PRECIOS_PROMOCIONES,
                M13_COMPRAS, M14_PROVEEDORES, M15_PROVEEDORES_CTA_CTE,
                M27_TESORERIA, M28_CAJA_BANCOS, M29_GESTION_PAGOS, M30_GESTION_COBRANZAS, M31_IMPUESTOS,
                M48_REPORTES_DINAMICOS, M49_INDICADORES_KPI, M57_CENTRO_NOTIFICACIONES, M58_AUDITORIA_SISTEMA
        );

        // 2. Si tiene atención al público / mostrador
        if (empresa.isTienePuntoDeVenta()) {
            modulos.add(M10_PUNTO_DE_VENTA_POS);
            modulos.add(M23_CODIGOS_BARRA_QR);
        }

        // 3. Si maneja mercadería física / depósitos
        if (empresa.isManejaStock()) {
            modulos.addAll(EnumSet.of(
                    M17_CONTROL_RECEPCION, M18_INVENTARIO_STOCK, M19_GESTION_DEPOSITOS,
                    M20_TRANSFERENCIAS_STOCK, M22_AJUSTES_INVENTARIO, M54_ALERTAS_INTELIGENTES
            ));
        }

        // 4. Si es una industria o fábrica
        if (empresa.isRealizaManufactura()) {
            modulos.addAll(EnumSet.of(
                    M21_TRAZABILIDAD_LOTES, M40_GESTION_PRODUCCION, M41_PLANIFICACION_MRP,
                    M42_LISTAS_MATERIALES_BOM, M43_CONTROL_PROCESOS, M44_CONTROL_CALIDAD,
                    M45_MANTENIMIENTO, M46_COSTOS_FABRICACION
            ));
        }

        // 5. Si tiene personal a cargo
        if (empresa.isTieneEmpleados()) {
            modulos.addAll(EnumSet.of(
                    M11_VENDEDORES_COMISIONES, M34_GESTION_EMPLEADOS, M35_LIQUIDACION_SUELDOS,
                    M36_CONTROL_ASISTENCIA, M37_VACACIONES_LICENCIAS
            ));
        }

        // 6. Si tiene vehículos propios de reparto
        if (empresa.isTieneFlotaPropia()) {
            modulos.add(M24_LOGISTICA_DISTRIBUCION);
            modulos.add(M25_GESTION_FLOTA);
        }

        // 7. Si es Mediana o Gran Empresa (o pide contabilidad formal y BI avanzado)
        if (empresa.isRequiereContabilidadFormal() ||
                empresa.getTamano() == Empresa.TamanoEmpresa.MEDIANA ||
                empresa.getTamano() == Empresa.TamanoEmpresa.GRAN_EMPRESA) {
            modulos.addAll(EnumSet.of(
                    M07_CRM, M16_COMPARADOR_PRESUPUESTOS, M26_CONTABILIDAD_GENERAL,
                    M32_PRESUPUESTOS_FINANCIEROS, M33_COSTOS_RENTABILIDAD,
                    M38_EVALUACION_DESEMPENO, M39_RECLUTAMIENTO,
                    M47_BUSINESS_INTELLIGENCE, M50_PRONOSTICOS, M51_ANALISIS_COMPARATIVO,
                    M52_ASISTENTE_IA, M55_PREDICCION_DEMANDA, M56_RECONOCIMIENTO_DOCUMENTAL_OCR,
                    M59_SEGURIDAD_AVANZADA
            ));
        }

        empresa.setModulosActivos(modulos);
    }
}
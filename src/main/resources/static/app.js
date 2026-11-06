// Catálogo completo de los 66 módulos de MiERP (Sincronizado con CodigoModulo.java)
const CATALOGO_66_MODULOS = [
    { codigo: "M01_DASHBOARD_PRINCIPAL", nombre: "01. Dashboard Principal" },
    { codigo: "M02_EMPRESAS_SUCURSALES", nombre: "02. Empresas y Sucursales" },
    { codigo: "M03_USUARIOS_PERMISOS", nombre: "03. Usuarios y Permisos" },
    { codigo: "M04_CONFIGURACION_GENERAL", nombre: "04. Configuración General" },
    { codigo: "M05_GESTION_DOCUMENTAL", nombre: "05. Gestión Documental" },
    { codigo: "M06_VENTAS", nombre: "06. Gestión de Ventas" },
    { codigo: "M07_CRM", nombre: "07. CRM Comercial" },
    { codigo: "M08_CLIENTES_CTA_CTE", nombre: "08. Clientes y Cta. Cte." },
    { codigo: "M09_FACTURACION_ARCA", nombre: "09. Facturación Electrónica ARCA" },
    { codigo: "M10_PUNTO_DE_VENTA_POS", nombre: "10. Punto de Venta (POS)" },
    { codigo: "M11_VENDEDORES_COMISIONES", nombre: "11. Vendedores y Comisiones" },
    { codigo: "M12_PRECIOS_PROMOCIONES", nombre: "12. Listas de Precios y Promos" },
    { codigo: "M13_COMPRAS", nombre: "13. Órdenes de Compra" },
    { codigo: "M14_PROVEEDORES", nombre: "14. Gestión de Proveedores" },
    { codigo: "M15_PROVEEDORES_CTA_CTE", nombre: "15. Cta. Cte. Proveedores" },
    { codigo: "M16_COMPARADOR_PRESUPUESTOS", nombre: "16. Comparador Presupuestos" },
    { codigo: "M17_CONTROL_RECEPCION", nombre: "17. Control de Recepción" },
    { codigo: "M18_INVENTARIO_STOCK", nombre: "18. Inventario y Stock Real" },
    { codigo: "M19_GESTION_DEPOSITOS", nombre: "19. Múltiples Depósitos" },
    { codigo: "M20_TRANSFERENCIAS_STOCK", nombre: "20. Transferencias Sucursales" },
    { codigo: "M21_TRAZABILIDAD_LOTES", nombre: "21. Trazabilidad y Lotes" },
    { codigo: "M22_AJUSTES_INVENTARIO", nombre: "22. Ajustes de Inventario" },
    { codigo: "M23_CODIGOS_BARRA_QR", nombre: "23. Códigos de Barra y QR" },
    { codigo: "M24_LOGISTICA_DISTRIBUCION", nombre: "24. Logística y Despacho" },
    { codigo: "M25_GESTION_FLOTA", nombre: "25. Gestión de Flota" },
    { codigo: "M26_CONTABILIDAD_GENERAL", nombre: "26. Contabilidad (Libro Diario)" },
    { codigo: "M27_TESORERIA", nombre: "27. Tesorería y Flujo Fondos" },
    { codigo: "M28_CAJA_BANCOS", nombre: "28. Caja y Conciliación Bancos" },
    { codigo: "M29_GESTION_PAGOS", nombre: "29. Órdenes de Pago" },
    { codigo: "M30_GESTION_COBRANZAS", nombre: "30. Gestión de Cobranzas" },
    { codigo: "M31_IMPUESTOS", nombre: "31. Impuestos (IVA/Retenciones)" },
    { codigo: "M32_PRESUPUESTOS_FINANCIEROS", nombre: "32. Presupuestos Financieros" },
    { codigo: "M33_COSTOS_RENTABILIDAD", nombre: "33. Costos y Rentabilidad" },
    { codigo: "M34_GESTION_EMPLEADOS", nombre: "34. Legajos Digitales RRHH" },
    { codigo: "M35_LIQUIDACION_SUELDOS", nombre: "35. Liquidación de Sueldos" },
    { codigo: "M36_CONTROL_ASISTENCIA", nombre: "36. Control de Asistencia" },
    { codigo: "M37_VACACIONES_LICENCIAS", nombre: "37. Vacaciones y Licencias" },
    { codigo: "M38_EVALUACION_DESEMPENO", nombre: "38. Evaluación de Desempeño" },
    { codigo: "M39_RECLUTAMIENTO", nombre: "39. Selección y Reclutamiento" },
    { codigo: "M40_GESTION_PRODUCCION", nombre: "40. Órdenes de Producción" },
    { codigo: "M41_PLANIFICACION_MRP", nombre: "41. Planificación Materiales MRP" },
    { codigo: "M42_LISTAS_MATERIALES_BOM", nombre: "42. Fórmulas y Recetas BOM" },
    { codigo: "M43_CONTROL_PROCESOS", nombre: "43. Control Procesos Planta" },
    { codigo: "M44_CONTROL_CALIDAD", nombre: "44. Control de Calidad" },
    { codigo: "M45_MANTENIMIENTO", nombre: "45. Mantenimiento Industrial" },
    { codigo: "M46_COSTOS_FABRICACION", nombre: "46. Costos de Fabricación" },
    { codigo: "M47_BUSINESS_INTELLIGENCE", nombre: "47. Business Intelligence (BI)" },
    { codigo: "M48_REPORTES_DINAMICOS", nombre: "48. Reportes Dinámicos" },
    { codigo: "M49_INDICADORES_KPI", nombre: "49. Indicadores KPI" },
    { codigo: "M50_PRONOSTICOS", nombre: "50. Pronósticos Financieros" },
    { codigo: "M51_ANALISIS_COMPARATIVO", nombre: "51. Análisis Comparativo" },
    { codigo: "M52_ASISTENTE_IA", nombre: "52. Copiloto Inteligente IA" },
    { codigo: "M53_AUTOMATIZACION_PROCESOS", nombre: "53. Automatización Workflows" },
    { codigo: "M54_ALERTAS_INTELIGENTES", nombre: "54. Alertas Inteligentes" },
    { codigo: "M55_PREDICCION_DEMANDA", nombre: "55. Predicción de Demanda IA" },
    { codigo: "M56_RECONOCIMIENTO_DOCUMENTAL_OCR", nombre: "56. Lectura Facturas OCR" },
    { codigo: "M57_CENTRO_NOTIFICACIONES", nombre: "57. Centro de Notificaciones" },
    { codigo: "M58_AUDITORIA_SISTEMA", nombre: "58. Auditoría Inmutable" },
    { codigo: "M59_SEGURIDAD_AVANZADA", nombre: "59. Seguridad MFA / 2FA" },
    { codigo: "M60_COPIAS_SEGURIDAD", nombre: "60. Copias de Seguridad" },
    { codigo: "M61_API_INTEGRACIONES", nombre: "61. API & Webhooks" },
    { codigo: "M62_PORTAL_CLIENTES", nombre: "62. Portal Web Clientes" },
    { codigo: "M63_PORTAL_PROVEEDORES", nombre: "63. Portal Web Proveedores" },
    { codigo: "M64_APLICACION_MOVIL", nombre: "64. App Móvil Corporativa" },
    { codigo: "M65_INTEGRACION_BANCARIA", nombre: "65. Integración Open Banking" },
    { codigo: "M66_ECOMMERCE", nombre: "66. Sincronización E-Commerce" }
];

let empresasDisponibles = [];
let empresaActual = null;
let modoBackendConectado = true;

// Datos iniciales demostrativos para visualización inmediata
let productosDemo = [
    { sku: "INF-001", nombre: "Servidor Rack Empresarial 32GB", costoReposicion: 1450000, precioVentaBase: 2100000, alicuotaIva: 21 },
    { sku: "INF-002", nombre: "Lector Código de Barras Industrial QR", costoReposicion: 48000, precioVentaBase: 82000, alicuotaIva: 21 }
];
let ventasDemo = [
    { tipo: "FACTURA_A", numero: "0001-00000142", neto: 850000, iva: 178500, total: 1028500, cae: "74192837465019" }
];
let asientosDemo = [
    { fecha: "2026-11-05", concepto: "Asiento automático Venta FACTURA_A 1-142", origen: "FAC-A-142", debe: 1028500, haber: 1028500 }
];

window.addEventListener("DOMContentLoaded", () => {
    inicializarAplicacion();
});

async function inicializarAplicacion() {
    try {
        const resp = await fetch("/api/empresas");
        if (!resp.ok) throw new Error("API sin conexión a BD");
        empresasDisponibles = await resp.json();
        modoBackendConectado = true;
    } catch (e) {
        modoBackendConectado = false;
        const statusEl = document.getElementById("apiStatusIndicator");
        statusEl.textContent = "● Modo Demo Interactivo (Sin BD Remota)";
        statusEl.className = "status-pill demo";
    }

    if (empresasDisponibles.length === 0) {
        // Precargar 2 perfiles empresariales contrastantes (Comercio PyME vs Industria Corporativa)
        empresasDisponibles = [
            crearEmpresaSimulada(1, "Comercial Posadas S.R.L.", "30-71548921-4", "RESPONSABLE_INSCRIPTO", "PYME", true, true, false, true, false, false),
            crearEmpresaSimulada(2, "Industrias Metalúrgicas del NEA S.A.", "30-68991234-9", "RESPONSABLE_INSCRIPTO", "GRAN_EMPRESA", true, true, true, true, true, true)
        ];
    }

    empresaActual = empresasDisponibles[0];
    renderizarSelectorEmpresas();
    actualizarInterfazSegunTenant();
}

function crearEmpresaSimulada(id, razonSocial, cuit, condicionIva, tamano, pos, stock, manufactura, empleados, flota, contabilidad) {
    const modulos = new Set([
        "M01_DASHBOARD_PRINCIPAL", "M02_EMPRESAS_SUCURSALES", "M03_USUARIOS_PERMISOS",
        "M04_CONFIGURACION_GENERAL", "M05_GESTION_DOCUMENTAL", "M06_VENTAS",
        "M08_CLIENTES_CTA_CTE", "M09_FACTURACION_ARCA", "M12_PRECIOS_PROMOCIONES",
        "M13_COMPRAS", "M14_PROVEEDORES", "M15_PROVEEDORES_CTA_CTE",
        "M27_TESORERIA", "M28_CAJA_BANCOS", "M29_GESTION_PAGOS", "M30_GESTION_COBRANZAS",
        "M31_IMPUESTOS", "M48_REPORTES_DINAMICOS", "M49_INDICADORES_KPI",
        "M57_CENTRO_NOTIFICACIONES", "M58_AUDITORIA_SISTEMA"
    ]);

    if (pos) { modulos.add("M10_PUNTO_DE_VENTA_POS"); modulos.add("M23_CODIGOS_BARRA_QR"); }
    if (stock) {
        ["M17_CONTROL_RECEPCION", "M18_INVENTARIO_STOCK", "M19_GESTION_DEPOSITOS",
         "M20_TRANSFERENCIAS_STOCK", "M22_AJUSTES_INVENTARIO", "M54_ALERTAS_INTELIGENTES"].forEach(m => modulos.add(m));
    }
    if (manufactura) {
        ["M21_TRAZABILIDAD_LOTES", "M40_GESTION_PRODUCCION", "M41_PLANIFICACION_MRP",
         "M42_LISTAS_MATERIALES_BOM", "M43_CONTROL_PROCESOS", "M44_CONTROL_CALIDAD",
         "M45_MANTENIMIENTO", "M46_COSTOS_FABRICACION"].forEach(m => modulos.add(m));
    }
    if (empleados) {
        ["M11_VENDEDORES_COMISIONES", "M34_GESTION_EMPLEADOS", "M35_LIQUIDACION_SUELDOS",
         "M36_CONTROL_ASISTENCIA", "M37_VACACIONES_LICENCIAS"].forEach(m => modulos.add(m));
    }
    if (flota) { modulos.add("M24_LOGISTICA_DISTRIBUCION"); modulos.add("M25_GESTION_FLOTA"); }
    if (contabilidad || tamano === "MEDIANA" || tamano === "GRAN_EMPRESA") {
        ["M07_CRM", "M16_COMPARADOR_PRESUPUESTOS", "M26_CONTABILIDAD_GENERAL",
         "M32_PRESUPUESTOS_FINANCIEROS", "M33_COSTOS_RENTABILIDAD", "M38_EVALUACION_DESEMPENO",
         "M39_RECLUTAMIENTO", "M47_BUSINESS_INTELLIGENCE", "M50_PRONOSTICOS",
         "M51_ANALISIS_COMPARATIVO", "M52_ASISTENTE_IA", "M55_PREDICCION_DEMANDA",
         "M56_RECONOCIMIENTO_DOCUMENTAL_OCR", "M59_SEGURIDAD_AVANZADA"].forEach(m => modulos.add(m));
    }

    return { id, razonSocial, cuit, condicionIva, tamano, modulosActivos: Array.from(modulos) };
}

function renderizarSelectorEmpresas() {
    const select = document.getElementById("tenantSelect");
    select.innerHTML = "";
    empresasDisponibles.forEach(emp => {
        const opt = document.createElement("option");
        opt.value = emp.id;
        opt.textContent = `${emp.razonSocial} (${emp.tamano})`;
        if (empresaActual && emp.id === empresaActual.id) opt.selected = true;
        select.appendChild(opt);
    });
}

function cambiarEmpresaActiva() {
    const idSeleccionado = Number(document.getElementById("tenantSelect").value);
    empresaActual = empresasDisponibles.find(e => e.id === idSeleccionado);
    actualizarInterfazSegunTenant();
}

async function actualizarInterfazSegunTenant() {
    if (!empresaActual) return;

    document.getElementById("infoFiscalTenant").textContent =
        `CUIT: ${empresaActual.cuit} | ${empresaActual.condicionIva} | ${empresaActual.tamano}`;

    const modulosSet = new Set(empresaActual.modulosActivos || []);
    document.getElementById("badgeModulosCount").textContent = `${modulosSet.size}/66`;

    // Bloquear o habilitar menús laterales según los módulos activos del Tenant
    document.querySelectorAll(".nav-item[data-mod]").forEach(link => {
        const modReq = link.getAttribute("data-mod");
        if (modulosSet.has(modReq)) {
            link.classList.remove("locked");
            link.title = "Módulo Habilitado";
        } else {
            link.classList.add("locked");
            link.title = `Bloqueado por @RequiereModulo(${modReq}) para esta empresa`;
        }
    });

    renderizarMatriz66Modulos(modulosSet);
    await cargarDatosDashboard();
    renderizarTablasOperativas();
}

function renderizarMatriz66Modulos(modulosSet) {
    const contenedor = document.getElementById("grid66Modulos");
    contenedor.innerHTML = "";
    CATALOGO_66_MODULOS.forEach(mod => {
        const activo = modulosSet.has(mod.codigo);
        const div = document.createElement("div");
        div.className = `modulo-pill ${activo ? "activo" : "inactivo"}`;
        div.innerHTML = `<span>${mod.nombre}</span> <strong>${activo ? "ACTIVO" : "🔒"}</strong>`;
        contenedor.appendChild(div);
    });
}

async function cargarDatosDashboard() {
    if (modoBackendConectado && empresaActual.id) {
        try {
            const resp = await fetch("/api/dashboard/resumen", {
                headers: { "X-Empresa-Id": empresaActual.id }
            });
            if (resp.ok) {
                const data = await resp.json();
                pintarKPIs(data.totalVentasFacturadas, data.cantidadFacturasEmitidas, data.proyeccionVentasProximoMes,
                           data.cuentasPorCobrarClientes, data.deudaTotalProveedores, data.detalleAlertasCriticas);
                return;
            }
        } catch (err) { /* Usa respaldo local */ }
    }

    const totalVentas = ventasDemo.reduce((acc, v) => acc + v.total, 0);
    pintarKPIs(
        totalVentas,
        ventasDemo.length,
        Math.round(totalVentas * 1.12),
        420000,
        185000,
        [
            "STOCK CRÍTICO (M54): Lector Código de Barras Industrial QR por debajo del mínimo en Depósito Central.",
            `ARCA (M09): Punto de Venta Electrónico 0001 operativo con certificado digital activo para ${empresaActual.razonSocial}.`
        ]
    );
}

function pintarKPIs(ventas, cantFacturas, proyeccion, porCobrar, porPagar, alertas) {
    const fmt = n => "$" + Number(n).toLocaleString("es-AR", { minimumFractionDigits: 2 });
    document.getElementById("kpiVentas").textContent = fmt(ventas);
    document.getElementById("kpiCantidadFacturas").textContent = `${cantFacturas} comprobantes emitidos`;
    document.getElementById("kpiProyeccion").textContent = fmt(proyeccion);
    document.getElementById("kpiPorCobrar").textContent = fmt(porCobrar);
    document.getElementById("kpiPorPagar").textContent = fmt(porPagar);

    const ul = document.getElementById("listaAlertasDashboard");
    ul.innerHTML = "";
    (alertas.length ? alertas : ["Sin alertas críticas pendientes en este momento."]).forEach(a => {
        const li = document.createElement("li");
        li.textContent = a;
        ul.appendChild(li);
    });
}

function mostrarVista(nombreVista) {
    const link = document.querySelector(`.nav-item[data-view="${nombreVista}"]`);
    if (link && link.classList.contains("locked")) {
        const modReq = link.getAttribute("data-mod");
        alert(`⛔ ACCESO DENEGADO POR @RequiereModulo(${modReq})\n\nLa empresa "${empresaActual.razonSocial}" (${empresaActual.tamano}) no tiene habilitado este módulo según sus características actuales.\n\nPuedes cambiar a una empresa Industrial/Corporativa en el selector superior izquierdo o dar de alta una empresa con esa característica.`);
        return;
    }

    document.querySelectorAll(".vista-section").forEach(sec => sec.classList.remove("active"));
    const target = document.getElementById(`vista-${nombreVista}`);
    if (target) target.classList.add("active");

    document.querySelectorAll(".nav-item").forEach(item => item.classList.remove("active"));
    if (link) link.classList.add("active");
}

async function registrarNuevaEmpresa(event) {
    event.preventDefault();
    const payload = {
        razonSocial: document.getElementById("empRazonSocial").value,
        nombreFantasia: document.getElementById("empFantasia").value,
        cuit: document.getElementById("empCuit").value,
        condicionIva: document.getElementById("empIva").value,
        tamano: document.getElementById("empTamano").value,
        tienePuntoDeVenta: document.getElementById("chkPos").checked,
        manejaStock: document.getElementById("chkStock").checked,
        realizaManufactura: document.getElementById("chkManufactura").checked,
        tieneEmpleados: document.getElementById("chkEmpleados").checked,
        tieneFlotaPropia: document.getElementById("chkFlota").checked,
        requiereContabilidadFormal: document.getElementById("chkContabilidad").checked
    };

    if (modoBackendConectado) {
        try {
            const resp = await fetch("/api/empresas/onboarding", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });
            if (resp.ok) {
                const creada = await resp.json();
                empresasDisponibles.push(creada);
                empresaActual = creada;
                renderizarSelectorEmpresas();
                await actualizarInterfazSegunTenant();
                mostrarVista("catalogo");
                return;
            }
        } catch (e) { /* Fallback simulado */ }
    }

    const simulada = crearEmpresaSimulada(
        empresasDisponibles.length + 1,
        payload.razonSocial,
        payload.cuit,
        payload.condicionIva,
        payload.tamano,
        payload.tienePuntoDeVenta,
        payload.manejaStock,
        payload.realizaManufactura,
        payload.tieneEmpleados,
        payload.tieneFlotaPropia,
        payload.requiereContabilidadFormal
    );
    empresasDisponibles.push(simulada);
    empresaActual = simulada;
    renderizarSelectorEmpresas();
    actualizarInterfazSegunTenant();
    mostrarVista("catalogo");
}

function crearProducto(event) {
    event.preventDefault();
    productosDemo.push({
        sku: document.getElementById("prodSku").value,
        nombre: document.getElementById("prodNombre").value,
        costoReposicion: Number(document.getElementById("prodCosto").value),
        precioVentaBase: Number(document.getElementById("prodPrecio").value),
        alicuotaIva: Number(document.getElementById("prodIva").value)
    });
    event.target.reset();
    renderizarTablasOperativas();
}

// Lista de recibos de sueldo liquidados para poder imprimir cualquiera en PDF
let recibosDemo = [
    {
        legajo: "LEG-001",
        nombre: "Pérez Juan Ignacio",
        cuil: "20-34891203-9",
        cargo: "Analista Contable Senior",
        convenio: "Comercio 130/75",
        periodo: "2026-11",
        basico: 950000,
        antiguedad: 19000,
        presentismo: 80717,
        bruto: 1049717,
        jubilacion: 115469,
        ley19032: 31492,
        obraSocial: 31492,
        retenciones: 178453,
        neto: 871264
    }
];

function emitirFacturaVenta(event) {
    event.preventDefault();
    const tipo = document.getElementById("vtaTipo").value;
    const cliente = document.getElementById("vtaClienteNombre").value || "Consumidor Final";
    const concepto = document.getElementById("vtaConcepto").value || "Productos varios";
    const neto = Number(document.getElementById("vtaNeto").value);
    const iva = tipo === "FACTURA_C" ? 0 : Math.round(neto * 0.21);
    const total = neto + iva;
    const cae = tipo === "PRESUPUESTO" ? "NO FISCAL" : String(Math.floor(70000000000000 + Math.random() * 9000000000000));
    const num = `0001-00000${143 + ventasDemo.length}`;
    const fecha = new Date().toISOString().slice(0, 10);

    ventasDemo.unshift({ tipo, numero: num, cliente, concepto, fecha, neto, iva, total, cae });
    asientosDemo.unshift({
        fecha,
        concepto: `Asiento automático Venta ${tipo} ${num} (${cliente})`,
        origen: `${tipo}-${num}`,
        debe: total,
        haber: total
    });

    cargarDatosDashboard();
    renderizarTablasOperativas();
}

function simularLiquidacionSueldo() {
    const nombre = document.getElementById("rrhhNombre").value || "Gómez Laura (Legajo #014)";
    const cargo = document.getElementById("rrhhCargo").value || "Administración General";
    const basico = Number(document.getElementById("rrhhBasico").value) || 950000;

    const antiguedad = Math.round(basico * 0.01); // 1% antigüedad base
    const presentismo = Math.round((basico + antiguedad) * 0.0833); // 8.33% CCT 130/75
    const bruto = basico + antiguedad + presentismo;

    const jubilacion = Math.round(bruto * 0.11);
    const ley19032 = Math.round(bruto * 0.03);
    const obraSocial = Math.round(bruto * 0.03);
    const retenciones = jubilacion + ley19032 + obraSocial;
    const neto = bruto - retenciones;

    recibosDemo.unshift({
        legajo: `LEG-00${recibosDemo.length + 1}`,
        nombre,
        cuil: "27-38192837-4",
        cargo,
        convenio: "Comercio 130/75",
        periodo: new Date().toISOString().slice(0, 7),
        basico,
        antiguedad,
        presentismo,
        bruto,
        jubilacion,
        ley19032,
        obraSocial,
        retenciones,
        neto
    });

    renderizarTablasOperativas();
}

function renderizarTablasOperativas() {
    const fmt = n => "$" + Number(n).toLocaleString("es-AR");

    document.getElementById("tablaProductos").innerHTML = productosDemo.map(p =>
        `<tr><td><code>${p.sku}</code></td><td>${p.nombre}</td><td>${fmt(p.costoReposicion)}</td><td class="text-accent"><strong>${fmt(p.precioVentaBase * (1 + p.alicuotaIva / 100))}</strong></td></tr>`
    ).join("");

    document.getElementById("tablaVentas").innerHTML = ventasDemo.map((v, idx) =>
        `<tr>
            <td><strong>${v.tipo}</strong> ${v.numero}<br><small class="text-muted">${v.cliente || "Distribuidora Litoral S.R.L."}</small></td>
            <td>${fmt(v.neto)}</td>
            <td>${fmt(v.iva)}</td>
            <td class="text-success"><strong>${fmt(v.total)}</strong></td>
            <td><code>${v.cae}</code></td>
            <td><button class="btn-outline-sm" onclick="imprimirFacturaPDF(${idx})">🖨️ PDF</button></td>
        </tr>`
    ).join("");

    document.getElementById("tablaSueldos").innerHTML = recibosDemo.map((r, idx) =>
        `<tr>
            <td><strong>${r.nombre}</strong><br><small class="text-muted">${r.cargo} (${r.periodo})</small></td>
            <td>${fmt(r.bruto)}</td>
            <td class="text-warning">-${fmt(r.retenciones)}</td>
            <td class="text-success"><strong>${fmt(r.neto)}</strong></td>
            <td><span class="badge-version">LIQUIDADO</span></td>
            <td><button class="btn-outline-sm" onclick="imprimirReciboSueldoPDF(${idx})">🖨️ PDF</button></td>
        </tr>`
    ).join("");

    document.getElementById("tablaLibroDiario").innerHTML = asientosDemo.map(a =>
        `<tr><td>${a.fecha}</td><td>${a.concepto}</td><td><code>${a.origen}</code></td><td class="text-success">${fmt(a.debe)}</td><td class="text-accent">${fmt(a.haber)}</td></tr>`
    ).join("");

    document.getElementById("tablaCompras").innerHTML = `
        <tr><td><code>OC-2026-0089</code></td><td>Siderúrgica Acindar S.A.</td><td>30-50123456-7</td><td><span class="badge-version">RECIBIDA CONFORME</span></td><td>$1.850.000</td></tr>
        <tr><td><code>OC-2026-0090</code></td><td>Mayorista Tecnológico Sur</td><td>30-70987654-1</td><td><span class="badge-version">APROBADA</span></td><td>$640.000</td></tr>
    `;

    document.getElementById("tablaProduccion").innerHTML = `
        <tr><td><code>OP-2026-0014</code></td><td>Fórmula Estándar Estructura Metálica BOM v2</td><td>50 UN</td><td class="text-success">49 UN (1 Merma)</td><td>$3.450.000</td><td>FINALIZADA (Calidad OK)</td></tr>
    `;
}

// =========================================================================
// GENERADOR DE FACTURA ELECTRÓNICA ARCA (EX-AFIP) CON QR E IMPRESIÓN PDF
// =========================================================================
function imprimirFacturaPDF(index) {
    const v = ventasDemo[index];
    if (!v || !empresaActual) return;

    const letra = v.tipo.includes("_") ? v.tipo.split("_")[1] : "X";
    const codArca = letra === "A" ? "COD. 001" : (letra === "B" ? "COD. 006" : (letra === "C" ? "COD. 011" : "NO FISCAL"));
    const fmt = n => "$" + Number(n).toLocaleString("es-AR", { minimumFractionDigits: 2 });

    const ventana = window.open("", "_blank", "width=850,height=900");
    ventana.document.write(`
        <!DOCTYPE html>
        <html lang="es">
        <head>
            <meta charset="UTF-8">
            <title>${v.tipo} ${v.numero} - ${empresaActual.razonSocial}</title>
            <style>
                body { font-family: Arial, sans-serif; color: #111; margin: 30px; }
                .factura-box { border: 2px solid #000; padding: 20px; max-width: 780px; margin: auto; }
                .header { display: flex; justify-content: space-between; border-bottom: 2px solid #000; padding-bottom: 15px; position: relative; }
                .letra-box { border: 2px solid #000; width: 65px; height: 65px; text-align: center; background: #f8f9fa; }
                .letra-box h1 { margin: 4px 0 0; font-size: 36px; }
                .letra-box small { font-size: 9px; font-weight: bold; }
                .cliente-box { border-bottom: 1px solid #000; padding: 12px 0; font-size: 13px; display: flex; justify-content: space-between; }
                table { width: 100%; border-collapse: collapse; margin: 20px 0; font-size: 13px; }
                th { background: #e2e8f0; border: 1px solid #000; padding: 8px; text-align: left; }
                td { border: 1px solid #ccc; padding: 10px 8px; }
                .totales { text-align: right; margin-top: 20px; font-size: 14px; }
                .totales h3 { font-size: 20px; margin-top: 8px; }
                .footer-arca { display: flex; justify-content: space-between; align-items: center; border-top: 2px solid #000; padding-top: 15px; margin-top: 25px; font-size: 12px; }
                .qr-sim { width: 85px; height: 85px; border: 2px solid #000; display: flex; align-items: center; justify-content: center; font-size: 10px; text-align: center; font-weight: bold; background: repeating-linear-gradient(45deg, #eee, #eee 5px, #fff 5px, #fff 10px); }
                @media print { .no-print { display: none; } }
            </style>
        </head>
        <body>
            <div class="no-print" style="text-align:center; margin-bottom:15px;">
                <button onclick="window.print()" style="padding:10px 20px; background:#2563eb; color:#fff; border:none; border-radius:6px; cursor:pointer; font-weight:bold;">🖨️ Imprimir o Guardar como PDF</button>
            </div>
            <div class="factura-box">
                <div class="header">
                    <div style="width:42%;">
                        <h2 style="margin:0 0 6px;">${empresaActual.razonSocial}</h2>
                        <p style="margin:3px 0; font-size:12px;"><strong>Condición IVA:</strong> ${empresaActual.condicionIva}</p>
                        <p style="margin:3px 0; font-size:12px;"><strong>Domicilio Comercial:</strong> Av. Mitre 1250 - Posadas, Misiones</p>
                    </div>
                    <div class="letra-box">
                        <h1>${letra}</h1>
                        <small>${codArca}</small>
                    </div>
                    <div style="width:42%; text-align:right;">
                        <h2 style="margin:0 0 6px;">${v.tipo.replace("_", " ")}</h2>
                        <p style="margin:3px 0; font-size:13px;"><strong>Nro: ${v.numero}</strong></p>
                        <p style="margin:3px 0; font-size:12px;"><strong>Fecha de Emisión:</strong> ${v.fecha || "2026-11-05"}</p>
                        <p style="margin:3px 0; font-size:12px;"><strong>CUIT Emisor:</strong> ${empresaActual.cuit}</p>
                        <p style="margin:3px 0; font-size:12px;"><strong>Ingresos Brutos:</strong> ${empresaActual.cuit}</p>
                    </div>
                </div>

                <div class="cliente-box">
                    <div>
                        <strong>Cliente / Razón Social:</strong> ${v.cliente || "Distribuidora Litoral S.R.L."}<br>
                        <strong>Condición frente al IVA:</strong> IVA Responsable Inscripto
                    </div>
                    <div style="text-align:right;">
                        <strong>CUIT Receptor:</strong> 30-71009988-2<br>
                        <strong>Condición de Venta:</strong> Cuenta Corriente / Contado
                    </div>
                </div>

                <table>
                    <thead>
                        <tr><th>Código</th><th>Descripción / Concepto</th><th>Cant.</th><th>Precio Unit. Neto</th><th>Alíc. IVA</th><th>Subtotal c/IVA</th></tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>ITEM-01</td>
                            <td>${v.concepto || "Equipamiento y Servicios Empresariales"}</td>
                            <td>1.00 UN</td>
                            <td>${fmt(v.neto)}</td>
                            <td>${v.iva > 0 ? "21.00%" : "0.00%"}</td>
                            <td><strong>${fmt(v.total)}</strong></td>
                        </tr>
                    </tbody>
                </table>

                <div class="totales">
                    <p>Importe Neto Gravado: <strong>${fmt(v.neto)}</strong></p>
                    <p>IVA 21%: <strong>${fmt(v.iva)}</strong></p>
                    <p>Otros Tributos: <strong>$0,00</strong></p>
                    <h3>IMPORTE TOTAL: ${fmt(v.total)}</h3>
                </div>

                <div class="footer-arca">
                    <div class="qr-sim">CÓDIGO QR<br>OFICIAL<br>ARCA</div>
                    <div>
                        <strong>ARCA — Comprobante Autorizado</strong><br>
                        <small>Esta Administración no se responsabiliza por los datos ingresados en el detalle de la operación.</small><br>
                        <small>Sistema ERP: <strong>MiERP Enterprise © Tomás Godoy</strong></small>
                    </div>
                    <div style="text-align:right;">
                        <strong>CAE N°:</strong> ${v.cae}<br>
                        <strong>Fecha Vto. CAE:</strong> 2026-11-15
                    </div>
                </div>
            </div>
        </body>
        </html>
    `);
    ventana.document.close();
}

// =========================================================================
// GENERADOR DE RECIBO DE SUELDO DE LEY ARGENTINA (ART. 140 LCT) EN PDF
// =========================================================================
function imprimirReciboSueldoPDF(index) {
    const r = recibosDemo[index];
    if (!r || !empresaActual) return;

    const fmt = n => "$" + Number(n).toLocaleString("es-AR", { minimumFractionDigits: 2 });

    const ventana = window.open("", "_blank", "width=850,height=850");
    ventana.document.write(`
        <!DOCTYPE html>
        <html lang="es">
        <head>
            <meta charset="UTF-8">
            <title>Recibo de Haberes ${r.periodo} - ${r.nombre}</title>
            <style>
                body { font-family: Arial, sans-serif; color: #111; margin: 30px; }
                .recibo-box { border: 2px solid #000; padding: 20px; max-width: 780px; margin: auto; }
                .header { display: flex; justify-content: space-between; border-bottom: 2px solid #000; padding-bottom: 12px; }
                .emp-info { border-bottom: 1px solid #000; padding: 10px 0; font-size: 13px; display: flex; justify-content: space-between; }
                table { width: 100%; border-collapse: collapse; margin: 18px 0; font-size: 13px; }
                th { background: #e2e8f0; border: 1px solid #000; padding: 8px; text-align: left; }
                td { border: 1px solid #ccc; padding: 8px; }
                .firmas { display: flex; justify-content: space-between; margin-top: 55px; text-align: center; font-size: 12px; }
                .linea-firma { border-top: 1px solid #000; width: 240px; padding-top: 6px; }
                @media print { .no-print { display: none; } }
            </style>
        </head>
        <body>
            <div class="no-print" style="text-align:center; margin-bottom:15px;">
                <button onclick="window.print()" style="padding:10px 20px; background:#10b981; color:#fff; border:none; border-radius:6px; cursor:pointer; font-weight:bold;">🖨️ Imprimir o Guardar Recibo en PDF</button>
            </div>
            <div class="recibo-box">
                <div class="header">
                    <div>
                        <h2 style="margin:0;">${empresaActual.razonSocial}</h2>
                        <small>CUIT Empleador: ${empresaActual.cuit} | Posadas, Misiones</small>
                    </div>
                    <div style="text-align:right;">
                        <h3 style="margin:0;">RECIBO DE HABERES (Ley N° 20.744)</h3>
                        <small>Período Liquidado: <strong>${r.periodo}</strong></small>
                    </div>
                </div>

                <div class="emp-info">
                    <div>
                        <strong>Empleado/a:</strong> ${r.nombre}<br>
                        <strong>CUIL:</strong> ${r.cuil} | <strong>Legajo:</strong> ${r.legajo}
                    </div>
                    <div style="text-align:right;">
                        <strong>Categoría / Cargo:</strong> ${r.cargo}<br>
                        <strong>Convenio Colectivo:</strong> ${r.convenio}
                    </div>
                </div>

                <table>
                    <thead>
                        <tr><th>Concepto</th><th>Unidades / %</th><th>Haberes c/ Aporte</th><th>Retenciones / Descuentos</th></tr>
                    </thead>
                    <tbody>
                        <tr><td>001 - Sueldo Básico Mensual</td><td>30 días</td><td>${fmt(r.basico)}</td><td>-</td></tr>
                        <tr><td>002 - Adicional por Antigüedad</td><td>1.00%</td><td>${fmt(r.antiguedad)}</td><td>-</td></tr>
                        <tr><td>003 - Adicional Presentismo (Art. 40 CCT)</td><td>8.33%</td><td>${fmt(r.presentismo)}</td><td>-</td></tr>
                        <tr><td>101 - Jubilación (SIPA Ley 24.241)</td><td>11.00%</td><td>-</td><td>${fmt(r.jubilacion)}</td></tr>
                        <tr><td>102 - INSSJP / PAMI (Ley 19.032)</td><td>3.00%</td><td>-</td><td>${fmt(r.ley19032)}</td></tr>
                        <tr><td>103 - Obra Social Nacional</td><td>3.00%</td><td>-</td><td>${fmt(r.obraSocial)}</td></tr>
                    </tbody>
                    <tfoot>
                        <tr style="background:#f8fafc; font-weight:bold;">
                            <td colspan="2">SUBTOTALES</td>
                            <td>${fmt(r.bruto)}</td>
                            <td>-${fmt(r.retenciones)}</td>
                        </tr>
                    </tfoot>
                </table>

                <div style="text-align:right; font-size:18px; margin-top:10px;">
                    <strong>NETO A COBRAR (Acreditación Bancaria CBU): ${fmt(r.neto)}</strong>
                </div>

                <div class="firmas">
                    <div class="linea-firma">Firma del Empleador<br><strong>${empresaActual.razonSocial}</strong></div>
                    <div class="linea-firma">Firma de Conformidad del Empleado<br><small>Recibí duplicado e importe neto</small></div>
                </div>
            </div>
        </body>
        </html>
    `);
    ventana.document.close();
}

function enviarPreguntaRapida(texto) {
    document.getElementById("inputPreguntaIA").value = texto;
    consultarAsistenteIA(new Event("submit"));
}

async function consultarAsistenteIA(event) {
    if (event.preventDefault) event.preventDefault();
    const input = document.getElementById("inputPreguntaIA");
    const pregunta = input.value.trim();
    if (!pregunta) return;

    const chat = document.getElementById("chatIaHistorial");
    chat.innerHTML += `<div class="ia-msg user">${pregunta}</div>`;
    input.value = "";

    let respuestaTexto = "";
    if (modoBackendConectado && empresaActual.id) {
        try {
            const resp = await fetch("/api/ia/consultar", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "X-Empresa-Id": empresaActual.id
                },
                body: JSON.stringify({ pregunta })
            });
            if (resp.ok) {
                const data = await resp.json();
                respuestaTexto = `<strong>${data.analisisEjecutivo}</strong><br><br>• ` + (data.accionesRecomendadas || []).join("<br>• ");
            }
        } catch (e) { /* Usa motor local */ }
    }

    if (!respuestaTexto) {
        const totalVentas = ventasDemo.reduce((acc, v) => acc + v.total, 0);
        respuestaTexto = `<strong>Diagnóstico Ejecutivo para ${empresaActual.razonSocial}:</strong><br>` +
            `• La empresa opera con <strong>${empresaActual.modulosActivos.length} módulos activos</strong> sobre los 66 del sistema.<br>` +
            `• Facturación acumulada: <strong>$${totalVentas.toLocaleString("es-AR")}</strong> con proyección a 30 días de <strong>$${Math.round(totalVentas * 1.12).toLocaleString("es-AR")}</strong>.<br>` +
            `• Recomendación IA: Emitir Orden de Compra automática (M13) para reponer artículos con stock crítico y conciliar cobranzas pendientes en Tesorería (M28).`;
    }

    chat.innerHTML += `<div class="ia-msg bot">${respuestaTexto}</div>`;
    chat.scrollTop = chat.scrollHeight;
}
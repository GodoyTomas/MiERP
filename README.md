# 🏢 MiERP — Sistema ERP Modular Multi-Tenant con IA & Motor Dinámico de 66 Módulos

![Java](https://img.shields.io/badge/Java-17%20LTS-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0%20Cloud-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![License](https://img.shields.io/badge/Licencia-Todos%20los%20Derechos%20Reservados-red?style=for-the-badge)

**MiERP** es una plataforma **Enterprise Resource Planning (ERP)** moderna y escalable desarrollada en **Java con Spring Boot y MySQL**, diseñada bajo una arquitectura de **Monolito Modular Multi-Tenant impulsada por Feature Flags y Programación Orientada a Aspectos (AOP)**.

A diferencia de los ERPs rígidos tradicionales, **MiERP** cuenta con un **Motor de Onboarding Inteligente (`ConfiguradorModulosService`)** que evalúa las características operativas, fiscales y dimensionales de cada empresa al registrarse (Micro, PyME, Mediana o Gran Empresa) para activar dinámicamente solo los módulos necesarios de un catálogo total de **66 submódulos especializados**, escalando al instante a medida que la organización crece.

---

## 🚀 Aspectos Técnicos Destacados (Ingeniería de Software)

1. **Guardián de Módulos en Tiempo Real con Spring AOP (`@RequiereModulo`):**
    * Implementación de la anotación personalizada `@RequiereModulo` y el aspecto interceptor `ValidadorModuloAspect`.
    * Cada petición HTTP es validada en tiempo de ejecución contra el perfil activo del Tenant (`X-Empresa-Id`), bloqueando con estado `403 FORBIDDEN` cualquier intento de acceso a funcionalidades no contratadas o deshabilitadas para esa empresa.
2. **Arquitectura Orientada a Eventos Desacoplados (`ApplicationEventPublisher`):**
    * Los módulos operativos no tienen acoplamiento rígido entre sí.
    * Al emitir una factura en `sales` (`VentaConfirmadaEvent`) o recibir mercadería en `purchases` (`MercaderiaRecibidaEvent`), los módulos de **Inventario (`ActualizadorStockListener`)** y **Contabilidad (`ContabilidadAutomaticaListener`)** reaccionan de forma asíncrona/transaccional **únicamente si la empresa tiene dichos módulos habilitados**.
3. **Motor Contable Automático de Partida Doble:**
    * Validación estricta de balance contable ($\sum \text{Debe} = \sum \text{Haber}$) y generación automática de asientos en el Libro Diario ante operaciones de compra y venta.
4. **Localización Fiscal y Laboral Argentina:**
    * Soporte para condiciones frente al IVA (`RESPONSABLE_INSCRIPTO`, `MONOTRIBUTO`, `EXENTO`), emisión de comprobantes electrónicos con autorización **CAE de ARCA (ex-AFIP)** y liquidación de haberes con cálculo automático de antigüedad, presentismo y retenciones de ley (Jubilación 11%, Ley 19.032 3%, Obra Social 3%).
5. **Optimización Cloud Database (HikariCP Tuning):**
    * Configuración de pool de conexiones ajustada para entornos de base de datos MySQL en la nube (Filess.io), maximizando el rendimiento con bajo consumo de conexiones concurrentes.

---

## 🧩 Catálogo de los 66 Módulos del Sistema

El sistema organiza sus 66 capacidades en **10 áreas empresariales**:

| Área / Dominio | Paquete Java | Módulos Incluidos (1 al 66) |
| :--- | :--- | :--- |
| **1. Administración y Gestión** | `core.tenant` / `core.security` | **01.** Dashboard principal · **02.** Multi-empresa y sucursales · **03.** Usuarios, roles y permisos · **04.** Configuración general · **05.** Gestión documental |
| **2. Ventas y Comercialización** | `modules.sales` | **06.** Ventas (Presupuestos, Pedidos, Facturación) · **07.** CRM · **08.** Clientes y Cuentas Corrientes · **09.** Facturación Electrónica ARCA · **10.** Punto de Venta (POS) · **11.** Vendedores y Comisiones · **12.** Precios y Promociones |
| **3. Compras y Proveedores** | `modules.purchases` | **13.** Órdenes de Compra · **14.** Proveedores y Calificación · **15.** Cuentas Corrientes Proveedores · **16.** Comparador de Presupuestos · **17.** Control de Recepción y Calidad |
| **4. Stock, Depósitos y Logística** | `modules.inventory` | **18.** Inventario en Tiempo Real y Kardex · **19.** Gestión Multi-Depósito · **20.** Transferencias · **21.** Trazabilidad por Lotes · **22.** Ajustes de Inventario · **23.** Códigos de Barra y QR · **24.** Logística y Distribución · **25.** Gestión de Flota |
| **5. Finanzas y Contabilidad** | `modules.finance` | **26.** Contabilidad General (Libro Diario/Mayor y Plan de Cuentas) · **27.** Tesorería · **28.** Caja y Conciliación Bancaria · **29.** Gestión de Pagos · **30.** Gestión de Cobranzas · **31.** Impuestos (IVA/Retenciones) · **32.** Presupuestos Financieros · **33.** Costos y Rentabilidad |
| **6. Recursos Humanos** | `modules.hr` | **34.** Legajos Digitales de Empleados · **35.** Liquidación de Sueldos · **36.** Control de Asistencia y Horas Extras · **37.** Vacaciones y Licencias · **38.** Evaluación de Desempeño · **39.** Reclutamiento |
| **7. Producción y Manufactura** | `modules.manufacturing` | **40.** Órdenes de Producción · **41.** Planificación MRP · **42.** Fórmulas y Listas de Materiales (BOM) · **43.** Control de Procesos · **44.** Control de Calidad · **45.** Mantenimiento · **46.** Costos de Fabricación |
| **8. Business Intelligence (BI)** | `modules.bi` | **47.** Business Intelligence · **48.** Reportes Dinámicos · **49.** Indicadores KPI en Tiempo Real · **50.** Pronósticos Financieros · **51.** Análisis Comparativo |
| **9. Tecnología e IA** | `modules.ai` | **52.** Asistente Inteligente Conversacional con IA · **53.** Automatización de Procesos · **54.** Alertas Inteligentes (Stock Crítico y Deudas) · **55.** Predicción de Demanda · **56.** Reconocimiento Documental OCR · **57.** Centro de Notificaciones |
| **10. Seguridad e Integraciones** | `core.audit` / `core.security` | **58.** Auditoría del Sistema · **59.** Seguridad Avanzada (2FA/MFA) · **60.** Copias de Seguridad · **61.** API REST · **62.** Portal Clientes · **63.** Portal Proveedores · **64.** App Móvil · **65.** Integración Bancaria · **66.** E-commerce |

---

## 🏗️ Arquitectura de Paquetes

```text
com.godoytomas.mierp
├── core
│   ├── audit                   # Módulo 58: Registro inmutable de auditoría de operaciones
│   ├── security                # Módulos 3 y 59: Usuarios, Roles granulares, MFA y SecurityConfig
│   └── tenant                  # Módulo 2: Empresas, Sucursales, Enum de 66 Módulos y Aspecto AOP (@RequiereModulo)
├── modules
│   ├── ai                      # Módulos 52-57: Copiloto Ejecutivo IA y diagnóstico automatizado
│   ├── bi                      # Módulos 1 y 47-51: Motor de KPIs, alertas y proyecciones financieras
│   ├── finance                 # Módulos 26-33: Plan de cuentas, Partida Doble automática y Tesorería
│   ├── hr                      # Módulos 34-39: Legajos, Asistencia y Liquidación de Haberes de Ley
│   ├── inventory               # Módulos 18-25: Productos, Depósitos, Kardex y Listener de Stock
│   ├── manufacturing           # Módulos 40-46: Listas de Materiales (BOM), MRP y Control de Calidad
│   ├── purchases               # Módulos 13-17: Proveedores, Órdenes de Compra y Recepción
│   └── sales                   # Módulos 6-12: Clientes, Cuentas Corrientes y Facturación Electrónica ARCA
└── MierpApplication.java
```

---

## 🔌 Principales Endpoints REST

Todas las rutas operativas validan automáticamente la cabecera `X-Empresa-Id` mediante `@RequiereModulo`:

* **Onboarding y Empresas (`core.tenant`):**
    * `POST /api/empresas/onboarding` — Registra una empresa y autoconfigura sus módulos activos según sus características.
    * `GET /api/empresas` — Lista las empresas registradas y su matriz de módulos habilitados.
* **Dashboard y Business Intelligence (`modules.bi`):**
    * `GET /api/dashboard/resumen` — Devuelve KPIs de facturación, deuda, alertas de stock crítico y pronóstico a 30 días.
* **Asistente Inteligente con IA (`modules.ai`):**
    * `POST /api/ia/consultar` — Procesa consultas gerenciales en lenguaje natural sobre el estado real del ERP.
* **Inventario y Alertas (`modules.inventory`):**
    * `GET /api/inventario/productos` | `POST /api/inventario/productos`
    * `GET /api/inventario/alertas-stock-bajo` — Detecta artículos por debajo del stock mínimo de reposición.
* **Ventas y Facturación ARCA (`modules.sales`):**
    * `GET /api/ventas/clientes` | `POST /api/ventas/clientes`
    * `POST /api/ventas/facturar` — Emite comprobante, calcula IVA, controla límite de crédito, genera CAE ARCA y dispara eventos a Stock y Contabilidad.
* **Compras y Recepción (`modules.purchases`):**
    * `POST /api/compras/ordenes` — Genera Orden de Compra.
    * `POST /api/compras/ordenes/{id}/recibir` — Registra recepción conforme, actualiza costo de reposición, suma stock e impacta deuda.
* **Finanzas y Contabilidad (`modules.finance`):**
    * `GET /api/finanzas/libro-diario` | `GET /api/finanzas/plan-cuentas`
    * `POST /api/finanzas/tesoreria/movimientos` — Registra cobros y pagos por caja, banco, cheque o QR.
* **Recursos Humanos (`modules.hr`):**
    * `POST /api/rrhh/empleados` | `POST /api/rrhh/liquidar/{empleadoId}?periodo=2026-11`
* **Producción e Industria (`modules.manufacturing`):**
    * `POST /api/produccion/ordenes` | `POST /api/produccion/ordenes/{id}/control-calidad`

---

## ⚙️ Configuración y Ejecución Local

1. Clonar el repositorio y abrir en **IntelliJ IDEA** (JDK 17+ y Maven).
2. Crear el archivo `src/main/resources/application-secret.properties` (excluido en `.gitignore` por seguridad) con las credenciales de la base de datos MySQL en la nube:
   ```properties
   DB_HOST=tu_host_mysql
   DB_PORT=3307
   DB_NAME=mierp_db
   DB_USER=tu_usuario
   DB_PASS=tu_password
   ```
3. Ejecutar la clase principal `MierpApplication`. Hibernate inicializará y actualizará automáticamente el esquema relacional completo.

---

## ⚖️ Licencia y Derechos de Autor

**Copyright (c) 2026 Tomás Godoy. Todos los derechos reservados (All Rights Reserved).**

Este software, su arquitectura y su código fuente son propiedad exclusiva de **Tomás Godoy**. El código se publica en este repositorio únicamente con fines de demostración técnica y portafolio profesional. Queda estrictamente prohibida su reproducción, distribución, modificación o uso comercial sin autorización expresa y por escrito del autor. Consulta el archivo [LICENSE](./LICENSE) para más detalles.
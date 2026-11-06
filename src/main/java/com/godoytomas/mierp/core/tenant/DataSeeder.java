package com.godoytomas.mierp.core.tenant;

import com.godoytomas.mierp.modules.finance.ContabilidadService;
import com.godoytomas.mierp.modules.finance.CuentaContable;
import com.godoytomas.mierp.modules.inventory.Producto;
import com.godoytomas.mierp.modules.inventory.ProductoRepository;
import com.godoytomas.mierp.modules.purchases.Proveedor;
import com.godoytomas.mierp.modules.purchases.ProveedorRepository;
import com.godoytomas.mierp.modules.sales.Cliente;
import com.godoytomas.mierp.modules.sales.ClienteRepository;
import com.godoytomas.mierp.modules.sales.ComprobanteVenta;
import com.godoytomas.mierp.modules.sales.ComprobanteVentaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {

    private final EmpresaRepository empresaRepository;
    private final SucursalRepository sucursalRepository;
    private final ConfiguradorModulosService configuradorModulos;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;
    private final ComprobanteVentaRepository comprobanteVentaRepository;
    private final ContabilidadService contabilidadService;

    public DataSeeder(EmpresaRepository empresaRepository,
                      SucursalRepository sucursalRepository,
                      ConfiguradorModulosService configuradorModulos,
                      ProductoRepository productoRepository,
                      ClienteRepository clienteRepository,
                      ProveedorRepository proveedorRepository,
                      ComprobanteVentaRepository comprobanteVentaRepository,
                      ContabilidadService contabilidadService) {
        this.empresaRepository = empresaRepository;
        this.sucursalRepository = sucursalRepository;
        this.configuradorModulos = configuradorModulos;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.proveedorRepository = proveedorRepository;
        this.comprobanteVentaRepository = comprobanteVentaRepository;
        this.contabilidadService = contabilidadService;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // 1. Verificar o crear Tenant 1: PyME Comercial en Posadas
        Empresa pyme = empresaRepository.findByCuit("30-71548921-4").orElseGet(() -> {
            Empresa nueva = Empresa.builder()
                    .razonSocial("Comercial Posadas S.R.L.")
                    .nombreFantasia("Posadas Tech")
                    .cuit("30-71548921-4")
                    .condicionIva(Empresa.CondicionIva.RESPONSABLE_INSCRIPTO)
                    .tamano(Empresa.TamanoEmpresa.PYME)
                    .tienePuntoDeVenta(true)
                    .manejaStock(true)
                    .realizaManufactura(false)
                    .tieneEmpleados(true)
                    .tieneFlotaPropia(false)
                    .requiereContabilidadFormal(false)
                    .fechaAlta(LocalDateTime.now())
                    .build();
            configuradorModulos.autoconfigurarModulos(nueva);
            return empresaRepository.save(nueva);
        });

        // 2. Verificar o crear Tenant 2: Gran Empresa Industrial con los 66 módulos
        Empresa industrial = empresaRepository.findByCuit("30-68991234-9").orElseGet(() -> {
            Empresa nueva = Empresa.builder()
                    .razonSocial("Industrias Metalúrgicas del NEA S.A.")
                    .nombreFantasia("MetalNEA")
                    .cuit("30-68991234-9")
                    .condicionIva(Empresa.CondicionIva.RESPONSABLE_INSCRIPTO)
                    .tamano(Empresa.TamanoEmpresa.GRAN_EMPRESA)
                    .tienePuntoDeVenta(true)
                    .manejaStock(true)
                    .realizaManufactura(true)
                    .tieneEmpleados(true)
                    .tieneFlotaPropia(true)
                    .requiereContabilidadFormal(true)
                    .fechaAlta(LocalDateTime.now())
                    .build();
            configuradorModulos.autoconfigurarModulos(nueva);
            return empresaRepository.save(nueva);
        });

        // 3. Sembrar sucursales, productos, clientes y comprobantes de forma idempotente (sin duplicar)
        if (comprobanteVentaRepository.count() == 0) {
            sembrarDatosOperativos(pyme, new BigDecimal("1850000.00"), 141L);
            sembrarDatosOperativos(industrial, new BigDecimal("4200000.00"), 142L);

            // 4. Inicializar Plan de Cuentas Contable en la empresa Industrial (Módulo 26)
            contabilidadService.obtenerOCrearCuenta(industrial, "1.1.01.001", "Caja y Bancos Moneda Nacional", CuentaContable.TipoCuenta.ACTIVO);
            contabilidadService.obtenerOCrearCuenta(industrial, "1.1.02.001", "Deudores por Ventas", CuentaContable.TipoCuenta.ACTIVO);
            contabilidadService.obtenerOCrearCuenta(industrial, "1.1.04.001", "Mercaderías e Insumos Industriales", CuentaContable.TipoCuenta.ACTIVO);
            contabilidadService.obtenerOCrearCuenta(industrial, "2.1.01.001", "Proveedores Locales", CuentaContable.TipoCuenta.PASIVO);
            contabilidadService.obtenerOCrearCuenta(industrial, "2.1.03.001", "IVA Débito Fiscal (ARCA)", CuentaContable.TipoCuenta.PASIVO);
            contabilidadService.obtenerOCrearCuenta(industrial, "4.1.01.001", "Ventas de Productos y Servicios", CuentaContable.TipoCuenta.INGRESO_RESULTADO_POSITIVO);
        }
    }

    private void sembrarDatosOperativos(Empresa emp, BigDecimal netoBase, Long nroComprobante) {
        // Reutilizar sucursal si ya existe para esta empresa, o crearla
        Sucursal sucursal = sucursalRepository.findByEmpresaId(emp.getId()).stream()
                .findFirst()
                .orElseGet(() -> sucursalRepository.save(Sucursal.builder()
                        .empresa(emp)
                        .codigo("SUC-001")
                        .nombre("Casa Central Posadas")
                        .direccion("Av. Mitre 1250")
                        .localidad("Posadas")
                        .provincia("Misiones")
                        .puntoVentaArca(1)
                        .esCasaMatriz(true)
                        .activa(true)
                        .build()));

        // Crear productos solo si la empresa aún no tiene ninguno cargado
        boolean tieneProductos = productoRepository.findAll().stream()
                .anyMatch(p -> p.getEmpresa().getId().equals(emp.getId()));

        if (!tieneProductos) {
            productoRepository.save(Producto.builder()
                    .empresa(emp)
                    .sku("INF-001-" + emp.getId())
                    .codigoBarras("7790001000012")
                    .nombre("Servidor Rack Empresarial 32GB")
                    .descripcion("Servidor para infraestructura crítica")
                    .tipo(Producto.TipoProducto.MERCADERIA)
                    .unidadMedida("UN")
                    .costoReposicion(new BigDecimal("1450000.00"))
                    .precioVentaBase(new BigDecimal("2100000.00"))
                    .alicuotaIva(new BigDecimal("21.00"))
                    .stockMinimoAlerta(new BigDecimal("3.00"))
                    .controlaLote(true)
                    .activo(true)
                    .build());

            productoRepository.save(Producto.builder()
                    .empresa(emp)
                    .sku("INF-002-" + emp.getId())
                    .codigoBarras("7790001000029")
                    .nombre("Lector Código de Barras Industrial QR")
                    .descripcion("Escáner láser inalámbrico para depósitos")
                    .tipo(Producto.TipoProducto.MERCADERIA)
                    .unidadMedida("UN")
                    .costoReposicion(new BigDecimal("48000.00"))
                    .precioVentaBase(new BigDecimal("82000.00"))
                    .alicuotaIva(new BigDecimal("21.00"))
                    .stockMinimoAlerta(new BigDecimal("10.00"))
                    .controlaLote(false)
                    .activo(true)
                    .build());
        }

        // Reutilizar cliente si ya existe para esta empresa, o crearlo
        Cliente cliente = clienteRepository.findAll().stream()
                .filter(c -> c.getEmpresa().getId().equals(emp.getId()))
                .findFirst()
                .orElseGet(() -> clienteRepository.save(Cliente.builder()
                        .empresa(emp)
                        .razonSocialNombre("Distribuidora Litoral S.R.L.")
                        .documentoFiscal("30-71009988-2")
                        .condicionIva(Empresa.CondicionIva.RESPONSABLE_INSCRIPTO)
                        .email("compras@distribuidoralitoral.com.ar")
                        .telefono("3764-458900")
                        .direccion("Av. Uruguay 3420, Posadas")
                        .limiteCredito(new BigDecimal("5000000.00"))
                        .saldoCuentaCorriente(new BigDecimal("420000.00"))
                        .activo(true)
                        .build()));

        // Crear proveedor solo si la empresa aún no tiene ninguno
        boolean tieneProveedores = proveedorRepository.findAll().stream()
                .anyMatch(pr -> pr.getEmpresa().getId().equals(emp.getId()));

        if (!tieneProveedores) {
            proveedorRepository.save(Proveedor.builder()
                    .empresa(emp)
                    .razonSocial("Siderúrgica & Tecnología Sur S.A.")
                    .cuit("30-50123456-7")
                    .condicionIva(Empresa.CondicionIva.RESPONSABLE_INSCRIPTO)
                    .nombreContacto("Ing. Martín Benítez")
                    .email("ventas@sidetech.com.ar")
                    .plazoPagoDias(30)
                    .calificacionDesempeno(5)
                    .saldoDeuda(new BigDecimal("185000.00"))
                    .activo(true)
                    .build());
        }

        // Emitir Factura A inicial para alimentar el Dashboard BI
        BigDecimal iva = netoBase.multiply(new BigDecimal("0.21"));
        BigDecimal total = netoBase.add(iva);

        comprobanteVentaRepository.save(ComprobanteVenta.builder()
                .empresa(emp)
                .sucursal(sucursal)
                .cliente(cliente)
                .tipo(ComprobanteVenta.TipoComprobante.FACTURA_A)
                .puntoVenta(1)
                .numeroComprobante(nroComprobante)
                .subtotalNeto(netoBase)
                .totalIva(iva)
                .totalFinal(total)
                .caeArca("74192837461920")
                .vencimientoCaeArca(LocalDate.now().plusDays(10))
                .fechaEmision(LocalDateTime.now())
                .build());
    }
}
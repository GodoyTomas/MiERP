package com.godoytomas.mierp.modules.bi;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.core.tenant.EmpresaRepository;
import com.godoytomas.mierp.modules.inventory.ExistenciaStock;
import com.godoytomas.mierp.modules.inventory.ExistenciaStockRepository;
import com.godoytomas.mierp.modules.purchases.Proveedor;
import com.godoytomas.mierp.modules.purchases.ProveedorRepository;
import com.godoytomas.mierp.modules.sales.Cliente;
import com.godoytomas.mierp.modules.sales.ClienteRepository;
import com.godoytomas.mierp.modules.sales.ComprobanteVenta;
import com.godoytomas.mierp.modules.sales.ComprobanteVentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessIntelligenceService {

    private final EmpresaRepository empresaRepository;
    private final ComprobanteVentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProveedorRepository proveedorRepository;
    private final ExistenciaStockRepository stockRepository;

    public BusinessIntelligenceService(EmpresaRepository empresaRepository,
                                       ComprobanteVentaRepository ventaRepository,
                                       ClienteRepository clienteRepository,
                                       ProveedorRepository proveedorRepository,
                                       ExistenciaStockRepository stockRepository) {
        this.empresaRepository = empresaRepository;
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.proveedorRepository = proveedorRepository;
        this.stockRepository = stockRepository;
    }

    public ResumenDashboardDTO construirDashboard(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada: " + empresaId));

        // 1. Sumarizar Facturación
        List<ComprobanteVenta> ventas = ventaRepository.findByEmpresaIdOrderByFechaEmisionDesc(empresaId);
        BigDecimal totalVentas = ventas.stream()
                .filter(v -> v.getTipo() != ComprobanteVenta.TipoComprobante.PRESUPUESTO)
                .map(ComprobanteVenta::getTotalFinal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 2. Sumarizar Cuentas por Cobrar (Clientes)
        BigDecimal porCobrar = clienteRepository.findByEmpresaIdAndActivoTrue(empresaId).stream()
                .map(Cliente::getSaldoCuentaCorriente)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. Sumarizar Cuentas por Pagar (Proveedores)
        BigDecimal porPagar = proveedorRepository.findByEmpresaIdAndActivoTrue(empresaId).stream()
                .map(Proveedor::getSaldoDeuda)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. Detectar Alertas Inteligentes (Stock Crítico y Deudas)
        List<String> alertas = new ArrayList<>();
        int contadorStockBajo = 0;

        if (empresa.getModulosActivos().contains(CodigoModulo.M18_INVENTARIO_STOCK)) {
            List<ExistenciaStock> criticos = stockRepository.buscarAlertasStockBajo(empresaId);
            contadorStockBajo = criticos.size();
            for (ExistenciaStock ex : criticos) {
                alertas.add("STOCK CRÍTICO: " + ex.getProducto().getNombre() +
                        " en depósito " + ex.getDeposito().getNombre() +
                        " (Disponible: " + ex.getCantidadDisponible() + ")");
            }
        }

        if (porPagar.compareTo(totalVentas) > 0 && porPagar.compareTo(BigDecimal.ZERO) > 0) {
            alertas.add("ALERTA FINANCIERA: La deuda exigible con proveedores ($" + porPagar +
                    ") supera la facturación acumulada.");
        }

        // 5. Pronóstico de Ventas (+12% tendencia proyectada - Módulo 50)
        BigDecimal proyeccion = totalVentas.multiply(new BigDecimal("1.12")).setScale(2, RoundingMode.HALF_UP);

        return ResumenDashboardDTO.builder()
                .razonSocialEmpresa(empresa.getRazonSocial())
                .tamanoEmpresa(empresa.getTamano().name())
                .cantidadModulosActivos(empresa.getModulosActivos().size())
                .modulosHabilitados(empresa.getModulosActivos().stream().map(Enum::name).collect(Collectors.toSet()))
                .totalVentasFacturadas(totalVentas)
                .cantidadFacturasEmitidas(ventas.size())
                .cuentasPorCobrarClientes(porCobrar)
                .deudaTotalProveedores(porPagar)
                .resultadoOperativoEstimado(totalVentas.subtract(porPagar))
                .alertasProductosStockBajo(contadorStockBajo)
                .detalleAlertasCriticas(alertas)
                .proyeccionVentasProximoMes(proyeccion)
                .build();
    }
}
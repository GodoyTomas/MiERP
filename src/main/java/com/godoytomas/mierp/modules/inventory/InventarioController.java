package com.godoytomas.mierp.modules.inventory;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiereModulo(CodigoModulo.M18_INVENTARIO_STOCK) // ¡El guardián AOP en acción!
public class InventarioController {

    private final ProductoRepository productoRepository;
    private final ExistenciaStockRepository existenciaStockRepository;

    public InventarioController(ProductoRepository productoRepository,
                                ExistenciaStockRepository existenciaStockRepository) {
        this.productoRepository = productoRepository;
        this.existenciaStockRepository = existenciaStockRepository;
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Producto>> listarProductos(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(productoRepository.findByEmpresaIdAndActivoTrue(empresaId));
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    // Módulo 54: Endpoint de Alertas Inteligentes de Stock Bajo
    @GetMapping("/alertas-stock-bajo")
    @RequiereModulo(CodigoModulo.M54_ALERTAS_INTELIGENTES)
    public ResponseEntity<List<ExistenciaStock>> obtenerAlertasStock(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(existenciaStockRepository.buscarAlertasStockBajo(empresaId));
    }
}
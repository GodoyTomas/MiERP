package com.godoytomas.mierp.modules.purchases;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@RequiereModulo(CodigoModulo.M13_COMPRAS)
public class ComprasController {

    private final ComprasService comprasService;
    private final ProveedorRepository proveedorRepository;
    private final OrdenCompraRepository ordenCompraRepository;

    public ComprasController(ComprasService comprasService,
                             ProveedorRepository proveedorRepository,
                             OrdenCompraRepository ordenCompraRepository) {
        this.comprasService = comprasService;
        this.proveedorRepository = proveedorRepository;
        this.ordenCompraRepository = ordenCompraRepository;
    }

    @GetMapping("/proveedores")
    @RequiereModulo(CodigoModulo.M14_PROVEEDORES)
    public ResponseEntity<List<Proveedor>> listarProveedores(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(proveedorRepository.findByEmpresaIdAndActivoTrue(empresaId));
    }

    @PostMapping("/proveedores")
    @RequiereModulo(CodigoModulo.M14_PROVEEDORES)
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorRepository.save(proveedor));
    }

    @PostMapping("/ordenes")
    public ResponseEntity<OrdenCompra> crearOrdenCompra(@RequestBody OrdenCompra orden) {
        return ResponseEntity.ok(comprasService.emitirOrdenCompra(orden));
    }

    @PostMapping("/ordenes/{ordenId}/recibir")
    @RequiereModulo(CodigoModulo.M17_CONTROL_RECEPCION)
    public ResponseEntity<OrdenCompra> recibirMercaderia(
            @PathVariable Long ordenId,
            @RequestParam(defaultValue = "Control de recepción conforme") String observaciones) {
        return ResponseEntity.ok(comprasService.registrarRecepcionMercaderia(ordenId, observaciones));
    }

    @GetMapping("/ordenes")
    public ResponseEntity<List<OrdenCompra>> listarOrdenes(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(ordenCompraRepository.findByEmpresaIdOrderByFechaCreacionDesc(empresaId));
    }
}
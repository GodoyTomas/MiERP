package com.godoytomas.mierp.modules.sales;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiereModulo(CodigoModulo.M06_VENTAS)
public class VentasController {

    private final FacturacionService facturacionService;
    private final ComprobanteVentaRepository comprobanteRepository;
    private final ClienteRepository clienteRepository;

    public VentasController(FacturacionService facturacionService,
                            ComprobanteVentaRepository comprobanteRepository,
                            ClienteRepository clienteRepository) {
        this.facturacionService = facturacionService;
        this.comprobanteRepository = comprobanteRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(clienteRepository.findByEmpresaIdAndActivoTrue(empresaId));
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PostMapping("/facturar")
    public ResponseEntity<ComprobanteVenta> emitirFactura(
            @RequestBody ComprobanteVenta comprobante,
            @RequestParam(required = false) Long depositoId,
            @RequestParam(defaultValue = "false") boolean cuentaCorriente) {
        return ResponseEntity.ok(facturacionService.emitirComprobante(comprobante, depositoId, cuentaCorriente));
    }

    @GetMapping("/comprobantes")
    public ResponseEntity<List<ComprobanteVenta>> listarComprobantes(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(comprobanteRepository.findByEmpresaIdOrderByFechaEmisionDesc(empresaId));
    }
}
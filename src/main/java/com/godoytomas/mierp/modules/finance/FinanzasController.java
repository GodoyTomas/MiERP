package com.godoytomas.mierp.modules.finance;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
public class FinanzasController {

    private final ContabilidadService contabilidadService;
    private final AsientoContableRepository asientoRepository;
    private final CuentaContableRepository cuentaRepository;
    private final MovimientoTesoreriaRepository tesoreriaRepository;

    public FinanzasController(ContabilidadService contabilidadService,
                              AsientoContableRepository asientoRepository,
                              CuentaContableRepository cuentaRepository,
                              MovimientoTesoreriaRepository tesoreriaRepository) {
        this.contabilidadService = contabilidadService;
        this.asientoRepository = asientoRepository;
        this.cuentaRepository = cuentaRepository;
        this.tesoreriaRepository = tesoreriaRepository;
    }

    @GetMapping("/plan-cuentas")
    @RequiereModulo(CodigoModulo.M26_CONTABILIDAD_GENERAL)
    public ResponseEntity<List<CuentaContable>> listarPlanCuentas(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(cuentaRepository.findByEmpresaIdOrderByCodigoAsc(empresaId));
    }

    @GetMapping("/libro-diario")
    @RequiereModulo(CodigoModulo.M26_CONTABILIDAD_GENERAL)
    public ResponseEntity<List<AsientoContable>> consultarLibroDiario(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(asientoRepository.findByEmpresaIdOrderByFechaContableDesc(empresaId));
    }

    @PostMapping("/asientos")
    @RequiereModulo(CodigoModulo.M26_CONTABILIDAD_GENERAL)
    public ResponseEntity<AsientoContable> crearAsientoManual(@RequestBody AsientoContable asiento) {
        asiento.setAutomatico(false);
        return ResponseEntity.ok(contabilidadService.registrarAsiento(asiento));
    }

    @GetMapping("/tesoreria/movimientos")
    @RequiereModulo(CodigoModulo.M27_TESORERIA)
    public ResponseEntity<List<MovimientoTesoreria>> listarFlujoFondos(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(tesoreriaRepository.findByEmpresaIdOrderByFechaMovimientoDesc(empresaId));
    }

    @PostMapping("/tesoreria/movimientos")
    @RequiereModulo(CodigoModulo.M27_TESORERIA)
    public ResponseEntity<MovimientoTesoreria> registrarCobroOPago(@RequestBody MovimientoTesoreria movimiento) {
        return ResponseEntity.ok(tesoreriaRepository.save(movimiento));
    }
}
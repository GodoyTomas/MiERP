package com.godoytomas.mierp.modules.hr;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rrhh")
@RequiereModulo(CodigoModulo.M34_GESTION_EMPLEADOS)
public class RecursosHumanosController {

    private final EmpleadoRepository empleadoRepository;
    private final LiquidacionSueldoRepository liquidacionRepository;
    private final RecursosHumanosService rrhhService;

    public RecursosHumanosController(EmpleadoRepository empleadoRepository,
                                     LiquidacionSueldoRepository liquidacionRepository,
                                     RecursosHumanosService rrhhService) {
        this.empleadoRepository = empleadoRepository;
        this.liquidacionRepository = liquidacionRepository;
        this.rrhhService = rrhhService;
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(empleadoRepository.findByEmpresaIdAndActivoTrue(empresaId));
    }

    @PostMapping("/empleados")
    public ResponseEntity<Empleado> registrarEmpleado(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoRepository.save(empleado));
    }

    @PostMapping("/liquidar/{empleadoId}")
    @RequiereModulo(CodigoModulo.M35_LIQUIDACION_SUELDOS)
    public ResponseEntity<LiquidacionSueldo> liquidarSueldo(
            @PathVariable Long empleadoId,
            @RequestParam String periodo) {
        return ResponseEntity.ok(rrhhService.liquidarSueldoMensual(empleadoId, periodo));
    }

    @GetMapping("/liquidaciones")
    @RequiereModulo(CodigoModulo.M35_LIQUIDACION_SUELDOS)
    public ResponseEntity<List<LiquidacionSueldo>> listarRecibos(
            @RequestHeader("X-Empresa-Id") Long empresaId,
            @RequestParam String periodo) {
        return ResponseEntity.ok(liquidacionRepository.findByEmpresaIdAndPeriodoAnioMes(empresaId, periodo));
    }
}
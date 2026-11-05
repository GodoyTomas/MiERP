package com.godoytomas.mierp.modules.manufacturing;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/produccion")
@RequiereModulo(CodigoModulo.M40_GESTION_PRODUCCION)
public class ProduccionController {

    private final OrdenProduccionRepository ordenProduccionRepository;

    public ProduccionController(OrdenProduccionRepository ordenProduccionRepository) {
        this.ordenProduccionRepository = ordenProduccionRepository;
    }

    @GetMapping("/ordenes")
    public ResponseEntity<List<OrdenProduccion>> listarOrdenes(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(ordenProduccionRepository.findByEmpresaIdOrderByFechaInicioDesc(empresaId));
    }

    // Módulo 41 y 46: Calcula el costo teórico del lote según la lista de materiales (BOM) al lanzar la orden
    @PostMapping("/ordenes")
    @RequiereModulo(CodigoModulo.M41_PLANIFICACION_MRP)
    public ResponseEntity<OrdenProduccion> lanzarOrdenProduccion(@RequestBody OrdenProduccion orden) {
        BigDecimal costoInsumosPorUnidad = BigDecimal.ZERO;

        if (orden.getFormula() != null && orden.getFormula().getInsumosRequeridos() != null) {
            for (ComponenteBOM comp : orden.getFormula().getInsumosRequeridos()) {
                BigDecimal costoInsumo = comp.getInsumo().getCostoReposicion()
                        .multiply(comp.getCantidadNecesariaPorUnidad());
                costoInsumosPorUnidad = costoInsumosPorUnidad.add(costoInsumo);
            }
            BigDecimal costoUnitarioTotal = costoInsumosPorUnidad.add(orden.getFormula().getCostoManoObraUnitario());
            orden.setCostoTotalFabricacion(
                    costoUnitarioTotal.multiply(orden.getCantidadPlanificada()).setScale(2, RoundingMode.HALF_UP)
            );
        }

        orden.setEstado(OrdenProduccion.EstadoProduccion.EN_PROCESO);
        return ResponseEntity.ok(ordenProduccionRepository.save(orden));
    }

    // Módulo 44: Aprobación de Control de Calidad y cierre de orden
    @PostMapping("/ordenes/{ordenId}/control-calidad")
    @RequiereModulo(CodigoModulo.M44_CONTROL_CALIDAD)
    public ResponseEntity<OrdenProduccion> finalizarConControlCalidad(
            @PathVariable Long ordenId,
            @RequestParam BigDecimal aprobadas,
            @RequestParam(defaultValue = "0") BigDecimal rechazadas,
            @RequestParam(defaultValue = "Lote aprobado según estándares") String dictamen) {

        OrdenProduccion orden = ordenProduccionRepository.findById(ordenId)
                .orElseThrow(() -> new IllegalArgumentException("Orden de producción no encontrada: " + ordenId));

        orden.setCantidadAprobadaCalidad(aprobadas);
        orden.setCantidadRechazadaCalidad(rechazadas);
        orden.setDictamenControlCalidad(dictamen);
        orden.setEstado(OrdenProduccion.EstadoProduccion.FINALIZADA);
        orden.setFechaFinalizacion(LocalDateTime.now());

        return ResponseEntity.ok(ordenProduccionRepository.save(orden));
    }
}
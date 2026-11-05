package com.godoytomas.mierp.modules.bi;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiereModulo(CodigoModulo.M01_DASHBOARD_PRINCIPAL)
public class DashboardBiController {

    private final BusinessIntelligenceService biService;

    public DashboardBiController(BusinessIntelligenceService biService) {
        this.biService = biService;
    }

    @GetMapping("/resumen")
    public ResponseEntity<ResumenDashboardDTO> obtenerPanelCentral(@RequestHeader("X-Empresa-Id") Long empresaId) {
        return ResponseEntity.ok(biService.construirDashboard(empresaId));
    }
}
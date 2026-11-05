package com.godoytomas.mierp.modules.ai;

import com.godoytomas.mierp.core.tenant.CodigoModulo;
import com.godoytomas.mierp.core.tenant.RequiereModulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ia")
@RequiereModulo(CodigoModulo.M52_ASISTENTE_IA)
public class AsistenteIaController {

    private final AsistenteInteligenteService iaService;

    public AsistenteIaController(AsistenteInteligenteService iaService) {
        this.iaService = iaService;
    }

    @PostMapping("/consultar")
    public ResponseEntity<ConsultaAsistenteDTO.RespuestaIA> consultarCopiloto(
            @RequestHeader("X-Empresa-Id") Long empresaId,
            @RequestBody ConsultaAsistenteDTO.PreguntaRequest request) {
        return ResponseEntity.ok(iaService.procesarConsulta(empresaId, request.getPregunta()));
    }
}
package com.godoytomas.mierp.modules.ai;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

public class ConsultaAsistenteDTO {

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class PreguntaRequest {
        private String pregunta;
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class RespuestaIA {
        private String preguntaOriginal;
        private String analisisEjecutivo;
        private List<String> accionesRecomendadas;
        private LocalDateTime fechaHora;
    }
}
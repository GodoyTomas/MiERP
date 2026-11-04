package com.godoytomas.mierp.core.tenant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ModuloNoHabilitadoException extends RuntimeException {
    public ModuloNoHabilitadoException(String mensaje) {
        super(mensaje);
    }
}
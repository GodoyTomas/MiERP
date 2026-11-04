package com.godoytomas.mierp.core.tenant;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ValidadorModuloAspect {

    private final EmpresaRepository empresaRepository;

    public ValidadorModuloAspect(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Before("@within(requiereModulo) || @annotation(requiereModulo)")
    public void validarAccesoModulo(RequiereModulo requiereModulo) {
        CodigoModulo moduloRequerido = requiereModulo.value();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }

        HttpServletRequest request = attributes.getRequest();
        String empresaIdHeader = request.getHeader("X-Empresa-Id");

        if (empresaIdHeader == null || empresaIdHeader.isBlank()) {
            throw new ModuloNoHabilitadoException("Debe especificar el encabezado X-Empresa-Id en la petición.");
        }

        Long empresaId = Long.parseLong(empresaIdHeader);
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new ModuloNoHabilitadoException("Empresa no encontrada con ID: " + empresaId));

        if (!empresa.getModulosActivos().contains(moduloRequerido)) {
            throw new ModuloNoHabilitadoException(
                    "Acceso denegado: La empresa '" + empresa.getRazonSocial() +
                            "' no tiene habilitado el módulo " + moduloRequerido.name()
            );
        }
    }
}
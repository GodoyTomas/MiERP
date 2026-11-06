package com.godoytomas.mierp.core.tenant;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Set;

@Aspect
@Component
public class ValidadorModuloAspect {

    private final EmpresaRepository empresaRepository;

    public ValidadorModuloAspect(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Before("@within(com.godoytomas.mierp.core.tenant.RequiereModulo) || @annotation(com.godoytomas.mierp.core.tenant.RequiereModulo)")
    public void validarAccesoModulo(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 1. Buscar la anotación en el método; si no está ahí, buscarla en la clase del controlador/servicio
        RequiereModulo requiereModulo = method.getAnnotation(RequiereModulo.class);
        if (requiereModulo == null) {
            requiereModulo = joinPoint.getTarget().getClass().getAnnotation(RequiereModulo.class);
        }

        if (requiereModulo == null) {
            return;
        }

        CodigoModulo moduloRequerido = requiereModulo.value();

        // 2. Obtener el HTTP Request actual para leer el Header X-Empresa-Id
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr == null) {
            return;
        }

        HttpServletRequest request = attr.getRequest();
        String empresaIdHeader = request.getHeader("X-Empresa-Id");

        Long empresaId = 1L;
        if (empresaIdHeader != null && !empresaIdHeader.isEmpty()) {
            try {
                empresaId = Long.parseLong(empresaIdHeader);
            } catch (NumberFormatException ignored) {}
        }

        Empresa empresa = empresaRepository.findById(empresaId).orElse(null);
        if (empresa == null) {
            throw new RuntimeException("Empresa no encontrada con ID: " + empresaId);
        }

        // 3. Validar si la empresa tiene el módulo activo en su Set<CodigoModulo>
        Set<CodigoModulo> modulosActivos = empresa.getModulosActivos();
        if (modulosActivos == null || !modulosActivos.contains(moduloRequerido)) {
            throw new RuntimeException("Acceso denegado: El módulo [" + moduloRequerido.name() + "] no está habilitado para la empresa " + empresa.getRazonSocial());
        }
    }
}
package com.godoytomas.mierp.core.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MiERP — API Empresarial Modular Multi-Tenant (66 Módulos)")
                        .version("1.0.0")
                        .description("Documentación interactiva de los endpoints REST protegidos por @RequiereModulo (Spring AOP). " +
                                "En las peticiones operativas, enviar X-Empresa-Id: 1 (PyME Comercial) o X-Empresa-Id: 2 (Gran Empresa Industrial).")
                        .contact(new Contact()
                                .name("Tomás Godoy")
                                .url("https://github.com/godoytomas"))
                        .license(new License()
                                .name("Copyright (c) 2026 Tomás Godoy - Todos los derechos reservados (All Rights Reserved)")));
    }
}
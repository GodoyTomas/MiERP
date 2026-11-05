package com.godoytomas.mierp.core.tenant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private final ConfiguradorModulosService configuradorModulosService;

    public EmpresaController(EmpresaRepository empresaRepository,
                             ConfiguradorModulosService configuradorModulosService) {
        this.empresaRepository = empresaRepository;
        this.configuradorModulosService = configuradorModulosService;
    }

    @PostMapping("/onboarding")
    public ResponseEntity<Empresa> registrarEmpresa(@RequestBody Empresa nuevaEmpresa) {
        configuradorModulosService.autoconfigurarModulos(nuevaEmpresa);
        Empresa guardada = empresaRepository.save(nuevaEmpresa);
        return ResponseEntity.ok(guardada);
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.ok(empresaRepository.findAll());
    }
}
package com.godoytomas.mierp.core.tenant;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "empresas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String razonSocial;

    @Column(length = 150)
    private String nombreFantasia;

    @Column(nullable = false, unique = true, length = 13)
    private String cuit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CondicionIva condicionIva; // RESPONSABLE_INSCRIPTO, MONOTRIBUTO, EXENTO

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private TamanoEmpresa tamano; // PYME, MEDIANA, GRAN_EMPRESA

    // Características operativas que definen qué módulos de los 66 se activan
    private boolean manejaStock;
    private boolean tienePuntoDeVenta;
    private boolean realizaManufactura;
    private boolean tieneEmpleados;
    private boolean tieneFlotaPropia;
    private boolean requiereContabilidadFormal;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "empresa_modulos_activos", joinColumns = @JoinColumn(name = "empresa_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "modulo_codigo", length = 50)
    private Set<CodigoModulo> modulosActivos = new HashSet<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaAlta = LocalDateTime.now();

    public enum CondicionIva { RESPONSABLE_INSCRIPTO, MONOTRIBUTO, EXENTO }
    public enum TamanoEmpresa { MICRO, PYME, MEDIANA, GRAN_EMPRESA }
}
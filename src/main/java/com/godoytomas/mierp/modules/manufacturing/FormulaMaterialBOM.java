package com.godoytomas.mierp.modules.manufacturing;

import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.modules.inventory.Producto;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formulas_bom")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class FormulaMaterialBOM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_terminado_id", nullable = false)
    private Producto productoTerminado;

    @Column(nullable = false, length = 100)
    private String nombreFormula; // Ej: "Fórmula Estándar Mesa Industrial v1"

    // Módulo 46: Costo operativo indirecto de mano de obra/energía por cada unidad fabricada
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal costoManoObraUnitario = BigDecimal.ZERO;

    @OneToMany(mappedBy = "formula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComponenteBOM> insumosRequeridos = new ArrayList<>();

    private boolean activa = true;
}
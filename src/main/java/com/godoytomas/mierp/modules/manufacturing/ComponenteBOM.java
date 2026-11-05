package com.godoytomas.mierp.modules.manufacturing;

import com.godoytomas.mierp.modules.inventory.Producto;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "componentes_bom")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ComponenteBOM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formula_id", nullable = false)
    private FormulaMaterialBOM formula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insumo_id", nullable = false)
    private Producto insumo;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantidadNecesariaPorUnidad;

    // Porcentaje estimado de desperdicio/merma en fábrica
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeMerma = BigDecimal.ZERO;
}
package com.godoytomas.mierp.modules.inventory;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "existencias_stock", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"producto_id", "deposito_id"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ExistenciaStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposito_id", nullable = false)
    private Deposito deposito;

    @Column(nullable = false, precision = 14, scale = 3)
    private BigDecimal cantidadDisponible = BigDecimal.ZERO;

    @Column(nullable = false, precision = 14, scale = 3)
    private BigDecimal cantidadReservada = BigDecimal.ZERO;

    @Column( length = 60)
    private String ubicacionPasilloEstante; // Ej: "Pasillo 2 - Nivel B"

    private LocalDateTime ultimaActualizacion = LocalDateTime.now();
}
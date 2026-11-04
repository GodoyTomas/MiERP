package com.godoytomas.mierp.modules.finance;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "renglones_asiento")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RenglonAsiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asiento_id", nullable = false)
    private AsientoContable asiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_contable_id", nullable = false)
    private CuentaContable cuenta;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal debe = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal haber = BigDecimal.ZERO;

    @Column(length = 150)
    private String leyenda;
}
package com.godoytomas.mierp.modules.hr;

import com.godoytomas.mierp.core.tenant.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "liquidaciones_sueldo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"empleado_id", "periodoAnioMes"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LiquidacionSueldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    // Formato YYYY-MM (Ej: "2026-11")
    @Column(nullable = false, length = 10)
    private String periodoAnioMes;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal sueldoBasico;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal adicionalAntiguedad = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal adicionalPresentismo = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalBrutoRemunerativo = BigDecimal.ZERO;

    // Retenciones de Ley Argentina (Jubilación 11%, Ley 19032 PAMI 3%, Obra Social 3%)
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal retencionJubilacion = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal retencionLey19032 = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal retencionObraSocial = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalDescuentos = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal netoACobrar = BigDecimal.ZERO;

    private LocalDate fechaLiquidacion = LocalDate.now();
    private boolean pagado = false;
}
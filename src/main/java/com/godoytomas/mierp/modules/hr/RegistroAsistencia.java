package com.godoytomas.mierp.modules.hr;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registros_asistencia")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RegistroAsistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    private Empleado empleado;

    @Column(nullable = false)
    private LocalDate fecha = LocalDate.now();

    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoPresencia estado = EstadoPresencia.PRESENTE;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal horasExtras50 = BigDecimal.ZERO;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal horasExtras100 = BigDecimal.ZERO;

    @Column(length = 200)
    private String observacionJustificacion;

    public enum EstadoPresencia {
        PRESENTE, AUSENTE_INJUSTIFICADO, LICENCIA_MEDICA, VACACIONES, FERIADO, FRANCO_COMPENSATORIO
    }
}
package com.godoytomas.mierp.modules.hr;

import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.core.tenant.Sucursal;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"empresa_id", "legajo"}),
        @UniqueConstraint(columnNames = {"empresa_id", "cuil"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @Column(nullable = false, length = 20)
    private String legajo; // Ej: "LEG-001"

    @Column(nullable = false, length = 15)
    private String cuil;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String puestoCargo;

    @Column(length = 100)
    private String convenioColectivo; // Ej: "Comercio 130/75", "UOM", "Fuera de Convenio"

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal sueldoBasicoBruto;

    @Column(length = 40)
    private String cbuHaberes;

    // Módulo 38: Puntaje de última evaluación de desempeño (1 a 100)
    private Integer puntajeDesempeno = 100;

    private boolean activo = true;
}
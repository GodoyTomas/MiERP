package com.godoytomas.mierp.modules.manufacturing;

import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.modules.inventory.Deposito;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_produccion")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrdenProduccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formula_bom_id", nullable = false)
    private FormulaMaterialBOM formula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposito_insumos_id", nullable = false)
    private Deposito depositoOrigenInsumos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposito_terminados_id", nullable = false)
    private Deposito depositoDestinoTerminados;

    @Column(nullable = false, length = 30)
    private String numeroOrden; // Ej: "OP-2026-0010"

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantidadPlanificada;

    // Módulo 44: Control de calidad (unidades aprobadas vs defectuosas)
    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantidadAprobadaCalidad = BigDecimal.ZERO;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantidadRechazadaCalidad = BigDecimal.ZERO;

    // Módulo 46: Costos totales reales de fabricación del lote
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal costoTotalFabricacion = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoProduccion estado = EstadoProduccion.PLANIFICADA_MRP;

    @Column(length = 250)
    private String dictamenControlCalidad;

    private LocalDateTime fechaInicio = LocalDateTime.now();
    private LocalDateTime fechaFinalizacion;

    public enum EstadoProduccion {
        PLANIFICADA_MRP, EN_PROCESO, CONTROL_CALIDAD, FINALIZADA, CANCELADA
    }
}
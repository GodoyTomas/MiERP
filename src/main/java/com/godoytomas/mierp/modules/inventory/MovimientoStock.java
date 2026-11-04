package com.godoytomas.mierp.modules.inventory;

import com.godoytomas.mierp.core.tenant.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_stock", indexes = {
        @Index(name = "idx_mov_empresa_fecha", columnList = "empresa_id, fechaHora")
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposito_id", nullable = false)
    private Deposito deposito;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoMovimiento tipo;

    @Column(nullable = false, precision = 14, scale = 3)
    private BigDecimal cantidad; // Positivo (+) suma stock, Negativo (-) descuenta

    @Column(nullable = false, precision = 14, scale = 3)
    private BigDecimal saldoResultante;

    // Módulo 21: Trazabilidad por lote y fecha de vencimiento
    @Column(length = 50)
    private String numeroLote;
    private LocalDate fechaVencimientoLote;

    // Referencia al comprobante que originó el movimiento (Ej: "FAC-A-0001-00001234" o "OC-0045")
    @Column(length = 60)
    private String comprobanteReferencia;

    @Column(length = 250)
    private String motivoObservacion;

    @Column(nullable = false, length = 80)
    private String usuarioResponsable;

    @Column(nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    public enum TipoMovimiento {
        INGRESO_COMPRA,
        EGRESO_VENTA,
        TRANSFERENCIA_ENTRADA,
        TRANSFERENCIA_SALIDA,
        AJUSTE_INVENTARIO_POSITIVO,
        AJUSTE_INVENTARIO_NEGATIVO,
        CONSUMO_PRODUCCION,
        ALTA_PRODUCCION
    }
}
package com.godoytomas.mierp.modules.finance;

import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.core.tenant.Sucursal;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_tesoreria")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MovimientoTesoreria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoFlujo tipoFlujo; // INGRESO_COBRANZA, EGRESO_PAGO

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MedioPago medioPago; // EFECTIVO_CAJA, TRANSFERENCIA_BANCARIA, CHEQUE, TARJETA, QR_BILLETERA

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, length = 180)
    private String concepto; // Ej: "Cobro Factura Cliente #14", "Orden de Pago Proveedor #8"

    @Column(length = 60)
    private String referenciaExterna; // Número de transferencia, cheque o cupón

    // Módulo 28: Conciliación bancaria
    private boolean conciliadoBanco = false;

    private LocalDateTime fechaMovimiento = LocalDateTime.now();

    public enum TipoFlujo {
        INGRESO_COBRANZA, EGRESO_PAGO
    }

    public enum MedioPago {
        EFECTIVO_CAJA, TRANSFERENCIA_BANCARIA, CHEQUE, TARJETA_CREDITO_DEBITO, QR_BILLETERA
    }
}
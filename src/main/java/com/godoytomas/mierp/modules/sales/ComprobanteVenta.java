package com.godoytomas.mierp.modules.sales;

import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.core.tenant.Sucursal;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comprobantes_venta")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ComprobanteVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private TipoComprobante tipo; // PRESUPUESTO, PEDIDO, REMITO, FACTURA_A, FACTURA_B, FACTURA_C, NOTA_CREDITO

    @Column(nullable = false)
    private Integer puntoVenta;

    @Column(nullable = false)
    private Long numeroComprobante;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotalNeto = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalIva = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalFinal = BigDecimal.ZERO;

    // Módulo 9: Datos fiscales ARCA (ex-AFIP)
    @Column(length = 20)
    private String caeArca;
    private LocalDate vencimientoCaeArca;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleComprobanteVenta> items = new ArrayList<>();

    private LocalDateTime fechaEmision = LocalDateTime.now();

    public enum TipoComprobante {
        PRESUPUESTO, PEDIDO, REMITO, FACTURA_A, FACTURA_B, FACTURA_C, NOTA_CREDITO, TICKET_POS
    }
}
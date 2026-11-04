package com.godoytomas.mierp.modules.sales;

import com.godoytomas.mierp.modules.inventory.Producto;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_comprobante_venta")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DetalleComprobanteVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprobante_id", nullable = false)
    private ComprobanteVenta comprobante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantidad;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal precioUnitario;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeDescuento = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotalLinea;
}
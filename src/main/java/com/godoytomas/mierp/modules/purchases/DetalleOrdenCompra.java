package com.godoytomas.mierp.modules.purchases;

import com.godoytomas.mierp.modules.inventory.Producto;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_orden_compra")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class DetalleOrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_compra_id", nullable = false)
    private OrdenCompra ordenCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantidadPedida;

    // Módulo 17: Verificación de cantidad física recibida en el depósito
    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal cantidadRecibida = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal costoUnitarioPactado;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotal;
}
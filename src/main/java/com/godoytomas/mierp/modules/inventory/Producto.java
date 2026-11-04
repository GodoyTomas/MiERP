package com.godoytomas.mierp.modules.inventory;

import com.godoytomas.mierp.core.tenant.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"empresa_id", "sku"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, length = 50)
    private String sku; // Código interno único

    // Módulo 23: Gestión de códigos de barras (EAN13) y QR
    @Column(length = 60)
    private String codigoBarras;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private TipoProducto tipo; // MERCADERIA, INSUMO, PRODUCTO_TERMINADO, SERVICIO

    @Column(nullable = false, length = 20)
    private String unidadMedida = "UN"; // UN, KG, LT, MT

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal costoReposicion = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal precioVentaBase = BigDecimal.ZERO;

    // Alícuota para ARCA (Ej: 21.00, 10.50, 0.00)
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal alicuotaIva = new BigDecimal("21.00");

    // Módulo 54: Para disparar alertas inteligentes de stock bajo
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal stockMinimoAlerta = BigDecimal.ZERO;

    // Módulo 21: Indica si exige trazabilidad por lote o vencimiento
    private boolean controlaLote = false;
    private boolean activo = true;

    public enum TipoProducto {
        MERCADERIA, INSUMO, PRODUCTO_TERMINADO, SERVICIO
    }
}
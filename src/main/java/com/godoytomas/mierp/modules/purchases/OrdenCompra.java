package com.godoytomas.mierp.modules.purchases;

import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.core.tenant.Sucursal;
import com.godoytomas.mierp.modules.inventory.Deposito;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordenes_compra")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class OrdenCompra {

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
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deposito_destino_id")
    private Deposito depositoDestino;

    @Column(nullable = false, length = 30)
    private String numeroOrden; // Ej: "OC-2026-0001"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 25)
    private EstadoOrden estado = EstadoOrden.BORRADOR;

    private LocalDate fechaEntregaEstimada;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalEstimado = BigDecimal.ZERO;

    @Column(length = 80)
    private String usuarioAutorizador;

    // Módulo 17: Observaciones de control de calidad al recibir la mercadería
    @Column(length = 300)
    private String observacionesRecepcion;

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrdenCompra> items = new ArrayList<>();

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaRecepcion;

    public enum EstadoOrden {
        BORRADOR, PENDIENTE_AUTORIZACION, APROBADA, RECIBIDA_PARCIAL, RECIBIDA_COMPLETA, CANCELADA
    }
}
package com.godoytomas.mierp.core.tenant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sucursales", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"empresa_id", "codigo"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, length = 20)
    private String codigo; // Ej: "SUC-01", "DEPOSITO-CENTRAL"

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 80)
    private String localidad;

    @Column(length = 80)
    private String provincia;

    // Punto de venta fiscal asociado en ARCA (Ej: 1 -> 00001)
    private Integer puntoVentaArca;

    private boolean esCasaMatriz = false;
    private boolean activa = true;
}
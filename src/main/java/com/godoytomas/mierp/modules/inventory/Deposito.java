package com.godoytomas.mierp.modules.inventory;

import com.godoytomas.mierp.core.tenant.Empresa;
import com.godoytomas.mierp.core.tenant.Sucursal;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "depositos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursal sucursal;

    @Column(nullable = false, length = 30)
    private String codigo; // Ej: "DEP-CENTRAL", "ESTANTERIA-A"

    @Column(nullable = false, length = 100)
    private String nombre;

    private boolean permiteDespacho = true;
    private boolean activo = true;
}
package com.godoytomas.mierp.modules.purchases;

import com.godoytomas.mierp.core.tenant.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "proveedores", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"empresa_id", "cuit"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, length = 150)
    private String razonSocial;

    @Column(nullable = false, length = 15)
    private String cuit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Empresa.CondicionIva condicionIva;

    @Column(length = 120)
    private String nombreContacto;

    @Column(length = 120)
    private String email;

    @Column(length = 40)
    private String telefono;

    @Column(length = 60)
    private String cbuAliasBancario;

    // Plazo de pago acordado en días (Ej: 30, 60 días)
    private Integer plazoPagoDias = 0;

    // Módulo 14: Evaluación de proveedor (1 a 5 estrellas según cumplimiento y calidad)
    private Integer calificacionDesempeno = 5;

    // Módulo 15: Cuenta corriente con el proveedor (Positivo = le debemos dinero)
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoDeuda = BigDecimal.ZERO;

    private boolean activo = true;
}
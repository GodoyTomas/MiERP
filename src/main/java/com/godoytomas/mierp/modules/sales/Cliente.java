package com.godoytomas.mierp.modules.sales;

import com.godoytomas.mierp.core.tenant.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "clientes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"empresa_id", "documentoFiscal"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, length = 150)
    private String razonSocialNombre;

    // CUIT, CUIL o DNI
    @Column(nullable = false, length = 15)
    private String documentoFiscal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Empresa.CondicionIva condicionIva;

    @Column(length = 120)
    private String email;

    @Column(length = 40)
    private String telefono;

    @Column(length = 250)
    private String direccion;

    // Módulo 8: Cuentas corrientes, saldos y límites de crédito
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal limiteCredito = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldoCuentaCorriente = BigDecimal.ZERO; // Positivo = nos debe

    private boolean activo = true;
}
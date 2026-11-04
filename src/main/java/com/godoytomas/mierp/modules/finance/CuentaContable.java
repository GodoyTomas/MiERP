package com.godoytomas.mierp.modules.finance;

import com.godoytomas.mierp.core.tenant.Empresa;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuentas_contables", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"empresa_id", "codigo"})
})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CuentaContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false, length = 30)
    private String codigo; // Ej: "1.1.01.001" (Caja), "4.1.01.001" (Ventas)

    @Column(nullable = false, length = 120)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoCuenta tipo; // ACTIVO, PASIVO, PATRIMONIO_NETO, INGRESO, EGRESO

    // Indica si recibe asientos directos (true) o si es una cuenta agrupadora/título (false)
    private boolean imputable = true;
    private boolean activa = true;

    public enum TipoCuenta {
        ACTIVO, PASIVO, PATRIMONIO_NETO, INGRESO_RESULTADO_POSITIVO, EGRESO_RESULTADO_NEGATIVO
    }
}
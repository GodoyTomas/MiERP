package com.godoytomas.mierp.modules.finance;

import com.godoytomas.mierp.core.tenant.Empresa;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asientos_contables")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AsientoContable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(nullable = false)
    private LocalDate fechaContable = LocalDate.now();

    @Column(nullable = false, length = 200)
    private String concepto; // Ej: "Asiento automático por Venta FAC-A-1-105"

    @Column(length = 60)
    private String comprobanteOrigen;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalDebe = BigDecimal.ZERO;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalHaber = BigDecimal.ZERO;

    private boolean automatico = true;

    @OneToMany(mappedBy = "asiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RenglonAsiento> renglones = new ArrayList<>();

    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
package com.godoytomas.mierp.modules.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExistenciaStockRepository extends JpaRepository<ExistenciaStock, Long> {

    Optional<ExistenciaStock> findByProductoIdAndDepositoId(Long productoId, Long depositoId);

    // Módulo 54: Alerta inteligente automática de productos con stock por debajo del mínimo
    @Query("SELECT e FROM ExistenciaStock e WHERE e.producto.empresa.id = :empresaId " +
            "AND e.cantidadDisponible <= e.producto.stockMinimoAlerta")
    List<ExistenciaStock> buscarAlertasStockBajo(Long empresaId);
}
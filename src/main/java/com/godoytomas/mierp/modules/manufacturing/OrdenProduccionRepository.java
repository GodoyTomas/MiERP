package com.godoytomas.mierp.modules.manufacturing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenProduccionRepository extends JpaRepository<OrdenProduccion, Long> {
    List<OrdenProduccion> findByEmpresaIdOrderByFechaInicioDesc(Long empresaId);
}
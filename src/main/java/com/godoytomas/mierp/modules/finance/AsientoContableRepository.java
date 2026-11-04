package com.godoytomas.mierp.modules.finance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AsientoContableRepository extends JpaRepository<AsientoContable, Long> {
    List<AsientoContable> findByEmpresaIdOrderByFechaContableDesc(Long empresaId);
}
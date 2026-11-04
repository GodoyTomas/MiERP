package com.godoytomas.mierp.modules.finance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaContableRepository extends JpaRepository<CuentaContable, Long> {
    List<CuentaContable> findByEmpresaIdOrderByCodigoAsc(Long empresaId);
    Optional<CuentaContable> findByEmpresaIdAndCodigo(Long empresaId, String codigo);
}
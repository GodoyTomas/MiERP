package com.godoytomas.mierp.modules.hr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LiquidacionSueldoRepository extends JpaRepository<LiquidacionSueldo, Long> {
    List<LiquidacionSueldo> findByEmpresaIdAndPeriodoAnioMes(Long empresaId, String periodoAnioMes);
}
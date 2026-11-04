package com.godoytomas.mierp.modules.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComprobanteVentaRepository extends JpaRepository<ComprobanteVenta, Long> {
    List<ComprobanteVenta> findByEmpresaIdOrderByFechaEmisionDesc(Long empresaId);
}
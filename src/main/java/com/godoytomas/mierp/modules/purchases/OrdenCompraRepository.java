package com.godoytomas.mierp.modules.purchases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
    List<OrdenCompra> findByEmpresaIdOrderByFechaCreacionDesc(Long empresaId);
}
package com.godoytomas.mierp.modules.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByEmpresaIdAndActivoTrue(Long empresaId);
    Optional<Producto> findByEmpresaIdAndSku(Long empresaId, String sku);
    Optional<Producto> findByEmpresaIdAndCodigoBarras(Long empresaId, String codigoBarras);
}
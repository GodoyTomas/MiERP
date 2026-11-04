package com.godoytomas.mierp.modules.finance;

import com.godoytomas.mierp.core.tenant.Empresa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ContabilidadService {

    private final AsientoContableRepository asientoRepository;
    private final CuentaContableRepository cuentaRepository;

    public ContabilidadService(AsientoContableRepository asientoRepository,
                               CuentaContableRepository cuentaRepository) {
        this.asientoRepository = asientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public AsientoContable registrarAsiento(AsientoContable asiento) {
        BigDecimal sumaDebe = BigDecimal.ZERO;
        BigDecimal sumaHaber = BigDecimal.ZERO;

        for (RenglonAsiento renglon : asiento.getRenglones()) {
            renglon.setAsiento(asiento);
            sumaDebe = sumaDebe.add(renglon.getDebe());
            sumaHaber = sumaHaber.add(renglon.getHaber());
        }

        // Regla de oro de la Partida Doble: El Debe tiene que ser exactamente igual al Haber
        if (sumaDebe.compareTo(sumaHaber) != 0) {
            throw new IllegalStateException(
                    "Asiento desbalanceado: Total Debe (" + sumaDebe + ") no coincide con Total Haber (" + sumaHaber + ")."
            );
        }

        asiento.setTotalDebe(sumaDebe);
        asiento.setTotalHaber(sumaHaber);
        return asientoRepository.save(asiento);
    }

    // Obtiene una cuenta contable estándar o la crea automáticamente si la empresa aún no cargó su plan
    @Transactional
    public CuentaContable obtenerOCrearCuenta(Empresa empresa, String codigo, String nombre, CuentaContable.TipoCuenta tipo) {
        return cuentaRepository.findByEmpresaIdAndCodigo(empresa.getId(), codigo)
                .orElseGet(() -> cuentaRepository.save(
                        CuentaContable.builder()
                                .empresa(empresa)
                                .codigo(codigo)
                                .nombre(nombre)
                                .tipo(tipo)
                                .imputable(true)
                                .activa(true)
                                .build()
                ));
    }
}
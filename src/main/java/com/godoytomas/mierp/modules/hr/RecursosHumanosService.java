package com.godoytomas.mierp.modules.hr;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Service
public class RecursosHumanosService {

    private final EmpleadoRepository empleadoRepository;
    private final LiquidacionSueldoRepository liquidacionRepository;

    public RecursosHumanosService(EmpleadoRepository empleadoRepository,
                                  LiquidacionSueldoRepository liquidacionRepository) {
        this.empleadoRepository = empleadoRepository;
        this.liquidacionRepository = liquidacionRepository;
    }

    @Transactional
    public LiquidacionSueldo liquidarSueldoMensual(Long empleadoId, String periodoAnioMes) {
        Empleado emp = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado: " + empleadoId));

        BigDecimal basico = emp.getSueldoBasicoBruto();

        // 1% por año de antigüedad
        int aniosAntiguedad = Math.max(0, Period.between(emp.getFechaIngreso(), LocalDate.now()).getYears());
        BigDecimal antiguedad = basico.multiply(new BigDecimal(aniosAntiguedad))
                .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

        // 8.33% de Presentismo (CCT 130/75 estándar)
        BigDecimal presentismo = basico.add(antiguedad)
                .multiply(new BigDecimal("0.0833")).setScale(2, RoundingMode.HALF_UP);

        BigDecimal bruto = basico.add(antiguedad).add(presentismo);

        // Descuentos de ley: 11% Jubilación, 3% Ley 19.032, 3% Obra Social
        BigDecimal jubilacion = bruto.multiply(new BigDecimal("0.11")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal ley19032 = bruto.multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal obraSocial = bruto.multiply(new BigDecimal("0.03")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal descuentos = jubilacion.add(ley19032).add(obraSocial);

        LiquidacionSueldo recibo = LiquidacionSueldo.builder()
                .empresa(emp.getEmpresa())
                .empleado(emp)
                .periodoAnioMes(periodoAnioMes)
                .sueldoBasico(basico)
                .adicionalAntiguedad(antiguedad)
                .adicionalPresentismo(presentismo)
                .totalBrutoRemunerativo(bruto)
                .retencionJubilacion(jubilacion)
                .retencionLey19032(ley19032)
                .retencionObraSocial(obraSocial)
                .totalDescuentos(descuentos)
                .netoACobrar(bruto.subtract(descuentos))
                .fechaLiquidacion(LocalDate.now())
                .pagado(false)
                .build();

        return liquidacionRepository.save(recibo);
    }
}
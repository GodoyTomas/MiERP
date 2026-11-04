package com.godoytomas.mierp.modules.sales;

import lombok.Getter;

@Getter
public class VentaConfirmadaEvent {
    private final ComprobanteVenta comprobante;
    private final Long depositoOrigenId;

    public VentaConfirmadaEvent(ComprobanteVenta comprobante, Long depositoOrigenId) {
        this.comprobante = comprobante;
        this.depositoOrigenId = depositoOrigenId;
    }
}
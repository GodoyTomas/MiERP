package com.godoytomas.mierp.modules.purchases;

import lombok.Getter;

@Getter
public class MercaderiaRecibidaEvent {
    private final OrdenCompra ordenCompra;

    public MercaderiaRecibidaEvent(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
}
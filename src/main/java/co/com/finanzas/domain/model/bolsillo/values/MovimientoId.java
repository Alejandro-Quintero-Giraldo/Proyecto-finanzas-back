package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.Identity;

public class MovimientoId extends Identity {
    public MovimientoId(String uuid) {
        super(uuid);
    }

    public static MovimientoId of(String uuid){
        return new MovimientoId(uuid);
    }
}

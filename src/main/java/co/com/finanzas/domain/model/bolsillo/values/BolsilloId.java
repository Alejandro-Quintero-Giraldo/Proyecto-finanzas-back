package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.Identity;

public class BolsilloId extends Identity {
    public BolsilloId(String uuid) {
        super(uuid);
    }

    public BolsilloId() {
    }

    public static BolsilloId of(String uuid) {
        return new BolsilloId(uuid);
    }
}

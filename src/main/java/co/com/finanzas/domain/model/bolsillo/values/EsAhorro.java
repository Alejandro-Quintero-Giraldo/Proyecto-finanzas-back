package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class EsAhorro implements ValueObject<Boolean> {
    public final Boolean value;

    public EsAhorro(Boolean value) {
        this.value = Objects.requireNonNull(value);
    }

    public static EsAhorro noEsAhorro(){
        return new EsAhorro(Boolean.FALSE);
    }

    @Override
    public Boolean value() {
        return value;
    }
}

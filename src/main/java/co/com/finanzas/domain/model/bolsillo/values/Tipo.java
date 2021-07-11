package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Tipo implements ValueObject<String> {
    public final String value;

    public Tipo(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static Tipo ingreso(){
        return new Tipo("Ingreso");
    }

    public static Tipo egreso(){
        return new Tipo("egreso");
    }

    @Override
    public String value() {
        return value;
    }
}

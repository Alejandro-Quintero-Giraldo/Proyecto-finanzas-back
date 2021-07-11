package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class PorcentajeAhorro implements ValueObject<Integer> {
    public final Integer value;

    public PorcentajeAhorro(Integer value) {
        if (value > 99 || value < 0){
            throw new IllegalArgumentException("No se pueden ingresar porcentajes mayores a 99 y menores a 0");
        }
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public java.lang.Integer value() {
        return value;
    }
}

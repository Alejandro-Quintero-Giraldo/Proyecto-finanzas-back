package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Saldo implements ValueObject<Integer> {
    public final Integer value;

    public Saldo(Integer value) {
        if(value <= 0){
            throw new IllegalArgumentException("No puedes ingresar un saldo con valores negativos, o igual a 0");
        }
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Integer value() {
        return value;
    }
}

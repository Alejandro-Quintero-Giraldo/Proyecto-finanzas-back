package co.com.finanzas.domain.model.bolsillo.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDateTime;

public class Fecha implements ValueObject<LocalDateTime> {
    public final LocalDateTime value;

    public Fecha() {
        this.value = LocalDateTime.now();
    }

    @Override
    public LocalDateTime value() {
        return this.value;
    }
}

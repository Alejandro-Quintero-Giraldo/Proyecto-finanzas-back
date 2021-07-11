package co.com.finanzas.domain.model.bolsillo.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.Objects;

public class IngresoMensualCalculado extends DomainEvent {
    private final Integer ingresosMensuales;

    public IngresoMensualCalculado(Integer ingresosMensuales) {
        super("finanzas.ingreso.mensual.calculado");
        this.ingresosMensuales = ingresosMensuales;
    }

    public Integer getIngresosMensuales() {
        return ingresosMensuales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngresoMensualCalculado that = (IngresoMensualCalculado) o;
        return Objects.equals(ingresosMensuales, that.ingresosMensuales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingresosMensuales);
    }

    @Override
    public String toString() {
        return "IngresoMensualCalculado{" +
                "ingresosMensuales=" + ingresosMensuales +
                '}';
    }
}

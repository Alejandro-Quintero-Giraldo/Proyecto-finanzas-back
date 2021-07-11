package co.com.finanzas.domain.model.bolsillo.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.Objects;

public class EgresoMensualCalculado  extends DomainEvent {
    private final Integer egresosMensuales;
    public EgresoMensualCalculado(Integer egresosMensuales) {
        super("finanzas.egresos.mensuales.calculados");
        this.egresosMensuales = egresosMensuales;
    }

    public Integer getEgresosMensuales() {
        return egresosMensuales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EgresoMensualCalculado that = (EgresoMensualCalculado) o;
        return Objects.equals(egresosMensuales, that.egresosMensuales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(egresosMensuales);
    }

    @Override
    public String toString() {
        return "EgresoMensualCalculado{" +
                "egresosMensuales=" + egresosMensuales +
                '}';
    }
}

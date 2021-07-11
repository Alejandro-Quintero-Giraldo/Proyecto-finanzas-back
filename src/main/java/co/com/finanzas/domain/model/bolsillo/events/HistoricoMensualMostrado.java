package co.com.finanzas.domain.model.bolsillo.events;

import co.com.sofka.domain.generic.DomainEvent;

import java.util.Objects;

public class HistoricoMensualMostrado extends DomainEvent {
    private final Integer ingresosMensuales;
    private final Integer egresosMensuales;

    public HistoricoMensualMostrado(Integer ingresosMensuales, Integer egresosMensuales) {
        super("finanzas.historico.mensual.creado");
        this.ingresosMensuales = ingresosMensuales;
        this.egresosMensuales = egresosMensuales;
    }

    public Integer getIngresosMensuales() {
        return ingresosMensuales;
    }

    public Integer getEgresosMensuales() {
        return egresosMensuales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoMensualMostrado that = (HistoricoMensualMostrado) o;
        return Objects.equals(ingresosMensuales, that.ingresosMensuales) && Objects.equals(egresosMensuales, that.egresosMensuales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingresosMensuales, egresosMensuales);
    }

    @Override
    public String toString() {
        return "HistoricoMensualMostrado{" +
                "ingresosMensuales=" + ingresosMensuales +
                ", egresosMensuales=" + egresosMensuales +
                '}';
    }
}

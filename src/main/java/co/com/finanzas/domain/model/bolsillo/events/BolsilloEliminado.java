package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Map;
import java.util.Objects;

public class BolsilloEliminado extends DomainEvent {
    private final EsEliminado esEliminado;


    public BolsilloEliminado(EsEliminado esEliminado ) {
        super("finanzas.bolsillo.eliminado");
        this.esEliminado = esEliminado;
        aggregateRootId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BolsilloEliminado that = (BolsilloEliminado) o;
        return Objects.equals(esEliminado, that.esEliminado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esEliminado);
    }

    public BolsilloId getBolsilloId() {
        return BolsilloId.of(aggregateRootId());
    }

    public EsEliminado getEsEliminado() {
        return esEliminado;
    }

    /*public Nombre getNombre() {
        return nombre;
    }

    public SaldoDisponible getSaldoDisponible() {
        return saldoDisponible;
    }

    public Map<MovimientoId, Movimiento> getMovimientos() {
        return movimientos;
    }

    public UsuarioId getuId() {
        return uId;
    }

    public EsAhorro getTipoAhorro() {
        return esAhorro;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }*/
}

package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Objects;

public class AhorroCreado extends DomainEvent {
    private final Nombre nombre;
    private final SaldoDisponible saldoDisponible;
    private final UsuarioId uid;
    private final EsAhorro esAhorro;
    private final PorcentajeAhorro porcentajeAhorro;

    public AhorroCreado(Nombre nombre, SaldoDisponible saldoDisponible, UsuarioId uid, EsAhorro esAhorro, PorcentajeAhorro porcentajeAhorro) {
        super("finanzas.ahorro.creado");
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.uid = uid;
        this.esAhorro = esAhorro;
        this.porcentajeAhorro = porcentajeAhorro;
        aggregateRootId();
    }

    public BolsilloId getBolsilloId(){
        return BolsilloId.of(aggregateRootId());
    }

    public Nombre getNombre() {
        return nombre;
    }

    public SaldoDisponible getSaldoDisponible() {
        return saldoDisponible;
    }

    public UsuarioId getUid() {
        return uid;
    }

    public EsAhorro getEsAhorro() {
        return esAhorro;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AhorroCreado that = (AhorroCreado) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(saldoDisponible, that.saldoDisponible) && Objects.equals(uid, that.uid) && Objects.equals(esAhorro, that.esAhorro) && Objects.equals(porcentajeAhorro, that.porcentajeAhorro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, saldoDisponible, uid, esAhorro, porcentajeAhorro);
    }
}

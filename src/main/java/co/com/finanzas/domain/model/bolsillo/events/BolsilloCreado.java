package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Map;
import java.util.Objects;

public class BolsilloCreado extends DomainEvent {
    private final Nombre nombre;
    private final SaldoDisponible saldoDisponible;
    private final Map<MovimientoId, Movimiento> movimientos;
    private final UsuarioId uId;
    private final EsAhorro esAhorro;
    private final PorcentajeAhorro porcentajeAhorro;


    public BolsilloCreado(Nombre nombre, SaldoDisponible saldoDisponible, Map<MovimientoId, Movimiento> movimientos, UsuarioId uId, EsAhorro esAhorro, PorcentajeAhorro porcentajeAhorro) {
        super("finanzas.bolsillo.creado");
        this.nombre = nombre;
        this.saldoDisponible = saldoDisponible;
        this.movimientos = movimientos;
        this.uId = uId;
        this.esAhorro = esAhorro;
        this.porcentajeAhorro = porcentajeAhorro;
        aggregateRootId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BolsilloCreado that = (BolsilloCreado) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(saldoDisponible, that.saldoDisponible) && Objects.equals(movimientos, that.movimientos) && Objects.equals(uId, that.uId) && Objects.equals(esAhorro, that.esAhorro) && Objects.equals(porcentajeAhorro, that.porcentajeAhorro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, saldoDisponible, movimientos, uId, esAhorro, porcentajeAhorro);
    }

    public Nombre getNombre() {
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
    }
}

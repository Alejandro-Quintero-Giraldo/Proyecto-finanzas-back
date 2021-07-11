package co.com.finanzas.domain.model.bolsillo.events;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.Map;
import java.util.Objects;

public class DineroIngresado extends DomainEvent {
    private final MovimientoId movimientoId;
    private final Tipo tipo;
    private final Fecha fecha;
    private final Saldo saldo;
    private final UsuarioId uid;
    public DineroIngresado(MovimientoId movimientoId, Tipo tipo, Fecha fecha, Saldo saldo,UsuarioId uid) {
        super("finanzas.dinero.ingresado");
        this.movimientoId = movimientoId;
        this.tipo = tipo;
        this.fecha = fecha;
        this.saldo = saldo;
        this.uid = uid;
        aggregateRootId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DineroIngresado that = (DineroIngresado) o;
        return Objects.equals(movimientoId, that.movimientoId) && Objects.equals(tipo, that.tipo) && Objects.equals(fecha, that.fecha) && Objects.equals(saldo, that.saldo) && Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movimientoId, tipo, fecha, saldo, uid);
    }

    public MovimientoId getMovimientoId() {
        return movimientoId;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public Saldo getSaldo() {
        return saldo;
    }

    public BolsilloId getBolsilloId(){
        return BolsilloId.of(aggregateRootId());
    }

    public UsuarioId getUid() {
        return uid;
    }
}

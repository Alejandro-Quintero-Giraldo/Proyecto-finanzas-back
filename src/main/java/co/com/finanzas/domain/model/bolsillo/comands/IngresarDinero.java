package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.Command;

import java.util.Map;

public class IngresarDinero implements Command {
    private final MovimientoId movimientoId;
    private final Tipo tipo;
    private final Fecha fecha;
    private final Saldo saldo;
    private final BolsilloId bolsilloId;
    private final UsuarioId uid;

    public IngresarDinero(MovimientoId movimientoId,Saldo saldo, BolsilloId bolsilloId,UsuarioId uid){
        this.movimientoId = movimientoId;
        this.tipo = Tipo.ingreso();
        this.fecha = new Fecha();
        this.saldo = saldo;
        this.bolsilloId = bolsilloId;
        this.uid = uid;
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

    public BolsilloId getBolsilloId() {
        return bolsilloId;
    }

    public UsuarioId getUid() {
        return uid;
    }
}

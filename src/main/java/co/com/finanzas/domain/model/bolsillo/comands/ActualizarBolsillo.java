package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.Command;

public class ActualizarBolsillo implements Command {

    private final BolsilloId bolsilloId;
    private final Nombre nombre;
    private final UsuarioId uid;
    private final PorcentajeAhorro porcentajeAhorro;

    public ActualizarBolsillo(BolsilloId bolsilloId, Nombre nombre, UsuarioId uid, PorcentajeAhorro porcentajeAhorro) {
        this.bolsilloId = bolsilloId;
        this.nombre = nombre;
        this.uid = uid;
        this.porcentajeAhorro = porcentajeAhorro;
    }

    public BolsilloId getBolsilloId() {
        return bolsilloId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public UsuarioId getUid() {
        return uid;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }
}

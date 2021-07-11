package co.com.finanzas.domain.model.bolsillo.comands;

import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.Command;

public class CrearAhorroAutomatico implements Command {
    private final BolsilloId bolsilloId;
    private final Nombre nombre;
    private final SaldoDisponible saldoDisponible;
    private final UsuarioId uid;
    private final EsAhorro esAhorro;
    private final PorcentajeAhorro porcentajeAhorro;

    public CrearAhorroAutomatico(BolsilloId bolsilloId, UsuarioId uid) {
        this.bolsilloId = bolsilloId;
        this.nombre = new Nombre("Ahorro");
        this.saldoDisponible = SaldoDisponible.inicializarSaldo();
        this.uid = uid;
        this.esAhorro = new EsAhorro(Boolean.TRUE);
        this.porcentajeAhorro = new PorcentajeAhorro(0);
    }

    public BolsilloId getBolsilloId() {
        return bolsilloId;
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
}

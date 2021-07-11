package co.com.finanzas.domain.model.bolsillo;

import co.com.finanzas.domain.model.bolsillo.values.*;

import java.util.Map;

public final class BolsilloBuilder {

    protected Nombre nombre;
    protected SaldoDisponible saldoDisponible;
    protected Map<MovimientoId, Movimiento> movimientos;
    protected UsuarioId uid;
    protected EsAhorro esAhorro;
    protected PorcentajeAhorro porcentajeAhorro;
    protected EsEliminado esEliminado;

    private BolsilloBuilder() {
    }

    public static BolsilloBuilder unBolsillo() {
        return new BolsilloBuilder();
    }

    public BolsilloBuilder withNombre(Nombre nombre) {
        this.nombre = nombre;
        return this;
    }

    public BolsilloBuilder withSaldoDisponible(SaldoDisponible saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
        return this;
    }

    public BolsilloBuilder withMovimientos(Map<MovimientoId, Movimiento> movimientos) {
        this.movimientos = movimientos;
        return this;
    }

    public BolsilloBuilder withUid(UsuarioId uid) {
        this.uid = uid;
        return this;
    }

    public BolsilloBuilder withEsAhorro(EsAhorro esAhorro) {
        this.esAhorro = esAhorro;
        return this;
    }

    public BolsilloBuilder withPorcentajeAhorro(PorcentajeAhorro porcentajeAhorro) {
        this.porcentajeAhorro = porcentajeAhorro;
        return this;
    }

    public BolsilloBuilder withEsEliminado(EsEliminado esEliminado) {
        this.esEliminado = esEliminado;
        return this;
    }

    public Bolsillo build(){
        return new Bolsillo(null, nombre,saldoDisponible,uid,esAhorro,porcentajeAhorro);
    }
}

package co.com.finanzas.domain.model.bolsillo.comands;


import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.domain.generic.Command;

import java.util.HashMap;
import java.util.Map;

public class CrearBolsillo implements Command {
    private final BolsilloId bolsilloId;
    private final Nombre nombre;
    private final SaldoDisponible saldoDisponible;
    private final Map<MovimientoId, Movimiento> movimientos;
    private final UsuarioId uId;
    private final EsAhorro esAhorro;
    private final PorcentajeAhorro porcentajeAhorro;

    public CrearBolsillo(BolsilloId bolsilloId, Nombre nombre, UsuarioId uId, PorcentajeAhorro porcentajeAhorro) {
        this.bolsilloId = bolsilloId;
        this.nombre = nombre;
        this.saldoDisponible = SaldoDisponible.inicializarSaldo();
        this.movimientos = new HashMap<>();
        this.uId = uId;
        this.esAhorro = EsAhorro.noEsAhorro();
        this.porcentajeAhorro = porcentajeAhorro;
    }

    public BolsilloId getBolsilloId() {
        return bolsilloId;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public SaldoDisponible getSaldoDisponible() {
        return this.saldoDisponible;
    }

    public Map<MovimientoId, Movimiento> getMovimientos() {
        return this.movimientos;
    }

    public UsuarioId getuId() {
        return uId;
    }

    public EsAhorro getEsAhorro() {
        return this.esAhorro;
    }

    public PorcentajeAhorro getPorcentajeAhorro() {
        return porcentajeAhorro;
    }

}

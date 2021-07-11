package co.com.finanzas.domain.model.bolsillo.factories;

import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.values.*;

import java.util.HashSet;
import java.util.Set;

public class MovimientoFactory {
    private final Set<Movimiento> movimientos;

    private MovimientoFactory(){
        movimientos = new HashSet<>();
    }

    public static MovimientoFactory builder()
    {
        return new MovimientoFactory();
    }

    public MovimientoFactory nuevoMovimiento(MovimientoId movimientoId, Tipo tipo, Fecha fecha, Saldo saldo, BolsilloId bolsilloId, UsuarioId uid){
        movimientos.add(new Movimiento(movimientoId,tipo,fecha,saldo,bolsilloId,uid));
        return this;
    }

    public Set<Movimiento> movimientos(){
        return movimientos;
    }
}

package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.infra.repository.IMovimientoDataRepository;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.IngresarDinero;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IngresarDineroUseCase extends UseCase<RequestCommand<IngresarDinero>, IngresarDineroUseCase.Response> {

    @Autowired
    private IMovimientoDataRepository data;

    @Autowired
    private IBolsilloDataRepository dataBolsillo;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

    @Override
    public void executeUseCase(RequestCommand<IngresarDinero> command){
        IngresarDinero ingresarDinero = command.getCommand();

        Movimiento movimiento = new Movimiento(
                ingresarDinero.getMovimientoId(),
                ingresarDinero.getTipo(),
                ingresarDinero.getFecha(),
                ingresarDinero.getSaldo(),
                ingresarDinero.getBolsilloId(),
                ingresarDinero.getUid()
                );
        MovimientoData movimientoData = transformar(movimiento);
        data.save(movimientoData);
        BolsilloData bolsilloData = aumentarSaldoDisponible(ingresarDinero.getBolsilloId(),ingresarDinero.getSaldo().value(), movimientoData, movimiento.identity().value());
        dataBolsillo.save(bolsilloData);
        emit().onResponse(new Response(movimiento));

    }

    public BolsilloData aumentarSaldoDisponible(BolsilloId bolsilloId, Integer saldo, MovimientoData movimiento, String movimientoId){
        BolsilloData bolsilloData = encontrarBolsillo(bolsilloId);
        Integer saldoDisponible = bolsilloData.getSaldoDisponible();
        Integer porcentaje = (saldo*bolsilloData.getPorcentajeAhorro())/100 ;
        Integer nuevoSaldo = (saldoDisponible + saldo) - porcentaje;
        Map<String, MovimientoData> movimientos = bolsilloData.getMovimientos();
        aumentarSaldoDeAhorro(porcentaje, bolsilloData.getUid());
        movimientos.put(movimientoId,movimiento);
        agregarMovimientoAlAhorro(movimiento,movimiento.getUid(),porcentaje);
        bolsilloData.setMovimientos(movimientos);
        bolsilloData.setSaldoDisponible(nuevoSaldo);
        return  bolsilloData;
    }

    public void agregarMovimientoAlAhorro(MovimientoData movimientoData, String uid, Integer porcentaje){
        Iterable<BolsilloData> bolsillos = transformacionBolsilloUseCase.listarPorUid(uid);

        for(BolsilloData bolsilloData: bolsillos){
            if(bolsilloData.getEsAhorro() == Boolean.TRUE || bolsilloData.getNombre().equals("Ahorro")){
                Map<String, MovimientoData> movimientos = bolsilloData.getMovimientos();
                movimientoData.setSaldo(porcentaje);
                movimientos.put(movimientoData.getMovimientoId(),movimientoData);
                bolsilloData.setMovimientos(movimientos);
                dataBolsillo.save(bolsilloData);
            }
        }
    }

    public void aumentarSaldoDeAhorro(Integer aumento, String uid){
        Iterable<BolsilloData> bolsillos = transformacionBolsilloUseCase.listarPorUid(uid);
        for(BolsilloData bolsillo: bolsillos){
            if(bolsillo.getEsAhorro() == Boolean.TRUE){
                Integer saldoAhorro = bolsillo.getSaldoDisponible();
                Integer nuevoSaldo = saldoAhorro + aumento;
                bolsillo.setSaldoDisponible(nuevoSaldo);
                dataBolsillo.save(bolsillo);
            }
        }
    }

    public BolsilloData encontrarBolsillo(BolsilloId bolsilloId){
         return transformacionBolsilloUseCase.listarPorId(bolsilloId.value());
    }

    public MovimientoData transformar(Movimiento movimiento){
        MovimientoData movimientoData = new MovimientoData(movimiento.identity().value(),movimiento.getTipo().value(),movimiento.getSaldo().value(),movimiento.getFecha().value(), movimiento.getBolsilloId().value(),movimiento.getUid().value());
        return  movimientoData;
    }

    public static class Response implements UseCase.ResponseValues{
        private Movimiento movimiento;

        public Response(Movimiento movimiento){
            this.movimiento = movimiento;
        }

        public Movimiento getResponse() {
            return movimiento;
        }

        public void setMovimiento(Movimiento movimiento) {
            this.movimiento = movimiento;
        }
    }


}

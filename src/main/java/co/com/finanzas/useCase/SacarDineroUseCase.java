package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.infra.repository.IMovimientoDataRepository;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import co.com.finanzas.domain.model.bolsillo.Movimiento;
import co.com.finanzas.domain.model.bolsillo.comands.SacarDinero;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class SacarDineroUseCase extends UseCase<RequestCommand<SacarDinero>, SacarDineroUseCase.Response>{

    @Autowired
    private IMovimientoDataRepository iMovimientoRepository;

    @Autowired
    private IBolsilloDataRepository dataBolsillo;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;


    @Override
    public void executeUseCase(RequestCommand<SacarDinero> sacarDineroRequestCommand) {
        SacarDinero command = sacarDineroRequestCommand.getCommand();

        Movimiento movimiento = new Movimiento(command.getMovimientoId(),command.getTipo(),command.getFecha(),command.getSaldo(),command.getBolsilloId(),command.getUid());

        MovimientoData movimientoData = transformar(movimiento);
        BolsilloData bolsilloData = disminuirSaldoDisponible(command.getBolsilloId(),command.getSaldo().value(), movimientoData, command.getMovimientoId().value());
        dataBolsillo.save(bolsilloData);
        emit().onResponse(new Response(movimiento));
    }

    public BolsilloData disminuirSaldoDisponible(BolsilloId bolsilloId, Integer saldo, MovimientoData movimiento, String movimientoId){
        BolsilloData bolsilloData = encontrarBolsillo(bolsilloId);
        Integer saldoDisponible = bolsilloData.getSaldoDisponible();
        if(saldoDisponible >= saldo) {
            Map<String, MovimientoData> movimientos = bolsilloData.getMovimientos();
            Integer nuevoSaldo = saldoDisponible - saldo;
            movimientos.put(movimientoId,movimiento);

            bolsilloData.setMovimientos(movimientos);
            bolsilloData.setSaldoDisponible(nuevoSaldo);
            iMovimientoRepository.save(movimiento);

        }
        else{
            throw new IllegalArgumentException("El saldo ingresado supera el saldo disponible en el bolsillo. Rectifique la información");
        }
        return  bolsilloData;
    }

    public BolsilloData encontrarBolsillo(BolsilloId bolsilloId){
        return transformacionBolsilloUseCase.listarPorId(bolsilloId.value());
    }

    public MovimientoData transformar(Movimiento movimiento){
        MovimientoData movimientoData = new MovimientoData(movimiento.identity().value(),movimiento.getTipo().value(),movimiento.getSaldo().value(),movimiento.getFecha().value(), movimiento.getBolsilloId().value(),movimiento.getUid().value());
        return  movimientoData;
    }

    public static class Response implements UseCase.ResponseValues{
        private Movimiento response;

        public Response(Movimiento response){
            this.response = response;
        }

        public Movimiento getResponse() {
            return response;
        }

        public void setResponse(Movimiento response) {
            this.response = response;
        }
    }
}


package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.CrearAhorroAutomatico;
import co.com.finanzas.domain.model.bolsillo.events.AhorroCreado;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
public class CrearAhorroAutomaticoUseCase  extends UseCase<RequestCommand<CrearAhorroAutomatico>, CrearAhorroAutomaticoUseCase.Response> {

    @Autowired
    private IBolsilloDataRepository data;

    @Override
    public void executeUseCase(RequestCommand<CrearAhorroAutomatico> crearAhorroAutomaticoRequestCommand) {
        CrearAhorroAutomatico command = crearAhorroAutomaticoRequestCommand.getCommand();
        Bolsillo bolsillo = new Bolsillo(
                command.getBolsilloId(),
                command.getNombre(),
                command.getSaldoDisponible(),
                command.getUid(),
                command.getEsAhorro(),
                command.getPorcentajeAhorro()
        );
        data.save(transformar(bolsillo));
        emit().onResponse(new Response(bolsillo));

    }

    public BolsilloData transformar(Bolsillo bolsillo){
        BolsilloData bolsilloData = new BolsilloData(bolsillo.getIdPro(),bolsillo.getNombre().value(), bolsillo.getSaldoDisponible().value(),bolsillo.getUid().value(),bolsillo.getEsAhorro().value(),bolsillo.getPorcentajeAhorro().value());
        return bolsilloData;
    }

    public static class Response implements UseCase.ResponseValues {
        private Bolsillo response;

        public Response(Bolsillo response){
            this.response = response;
        }

        public Bolsillo getResponse() {
            return response;
        }

        public void setResponse(Bolsillo response) {
            this.response = response;
        }
    }
}
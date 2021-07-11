package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.infra.repository.IUsuarioDataRepository;
import co.com.finanzas.domain.model.bolsillo.Bolsillo;
import co.com.finanzas.domain.model.bolsillo.comands.ActualizarBolsillo;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizarBolsilloUseCase extends UseCase<RequestCommand<ActualizarBolsillo>, ActualizarBolsilloUseCase.Response> {

    @Autowired
    private IBolsilloDataRepository data;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

    @Override
    public void executeUseCase(RequestCommand<ActualizarBolsillo> actualizarBolsilloRequestCommand) {
        ActualizarBolsillo command = actualizarBolsilloRequestCommand.getCommand();
        BolsilloData bolsillo = transformacionBolsilloUseCase.listarPorId(command.getBolsilloId().value());
        bolsillo.setNombre(command.getNombre().value());
        bolsillo.setPorcentajeAhorro(command.getPorcentajeAhorro().value());
        bolsillo.setUid(command.getUid().value());
        data.save(bolsillo);
        emit().onResponse(new Response(bolsillo));
    }

    public static class Response implements  UseCase.ResponseValues{
        private BolsilloData response;

        public Response(BolsilloData bolsillo){
            this.response = bolsillo;
        }

        public BolsilloData getResponse(){
            return  response;
        }

        public void setResponse(BolsilloData response){
            this.response = response;
        }
    }
}

package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.CrearAhorroAutomatico;
import co.com.finanzas.domain.model.bolsillo.events.AhorroCreado;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CrearAhorroAutomaticoUseCaseTest {

    CrearAhorroAutomaticoUseCase crearAhorroAutomaticoUseCase;

    @BeforeEach
    public  void setUp(){
        crearAhorroAutomaticoUseCase = new CrearAhorroAutomaticoUseCase();
    }

    @Test
    void crearBolsilloAhorro(){
        CrearAhorroAutomatico command = new CrearAhorroAutomatico(BolsilloId.of("yyyyyy"), UsuarioId.of("123"));

        AhorroCreado event = new AhorroCreado(
                command.getNombre(),
                command.getSaldoDisponible(),
                command.getUid(),
                command.getEsAhorro(),
                command.getPorcentajeAhorro()
        );
/*
        StepVerifier
                .create(crearAhorroAutomaticoUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();
*/

    }

}
package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.SacarDinero;
import co.com.finanzas.domain.model.bolsillo.events.DineroSacado;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import co.com.finanzas.domain.model.bolsillo.values.Saldo;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class SacarDineroUseCaseTest {

    SacarDineroUseCase sacarDineroUseCase;

    @BeforeEach
    public void setUp(){
        sacarDineroUseCase = new SacarDineroUseCase();
    }

    @Test
    void sacarDinero(){
        SacarDinero command = new SacarDinero(
                MovimientoId.of("xxxxx"),
                new Saldo(20000),
                BolsilloId.of("xxxxx"),
                UsuarioId.of("123")
        );

        DineroSacado event = new DineroSacado(
                command.getMovimientoId(),
                command.getTipo(),
                command.getFecha(),
                command.getSaldo(),
                command.getUid()
        );

        /*StepVerifier
                .create(sacarDineroUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();*/
    }

}
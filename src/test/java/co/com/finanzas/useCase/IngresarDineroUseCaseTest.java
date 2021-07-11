package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.IngresarDinero;
import co.com.finanzas.domain.model.bolsillo.events.DineroIngresado;
import co.com.finanzas.domain.model.bolsillo.values.BolsilloId;
import co.com.finanzas.domain.model.bolsillo.values.MovimientoId;
import co.com.finanzas.domain.model.bolsillo.values.Saldo;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class IngresarDineroUseCaseTest {

    IngresarDineroUseCase ingresarDineroUseCase;

    @BeforeEach
    public void setUp(){
        ingresarDineroUseCase = new IngresarDineroUseCase();
    }

    @Test
    void ingresarDinero(){
        IngresarDinero command = new IngresarDinero(
                MovimientoId.of("xxxxx"),
                new Saldo(20000),
                BolsilloId.of("xxxxx"),
                UsuarioId.of("123")
        );
        DineroIngresado event = new DineroIngresado(
                command.getMovimientoId(),
                command.getTipo(),
                command.getFecha(),
                command.getSaldo(),
                command.getUid()
        );
/*
        StepVerifier
                .create(ingresarDineroUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();*/
    }
}
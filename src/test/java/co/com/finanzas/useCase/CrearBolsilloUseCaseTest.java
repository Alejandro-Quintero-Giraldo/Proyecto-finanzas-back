package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.CrearBolsillo;
import co.com.finanzas.domain.model.bolsillo.values.*;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CrearBolsilloUseCaseTest {

    @Autowired
    private CrearBolsilloUseCase crearBolsilloUseCase;


    @Test
    public void CrearBolsilloUseCaseTest(){/*
        Mockito.when(crudRepository.save(any())).thenReturn(new Bolsillo(BolsilloId.of("xxxx"),
                        new Nombre("Transporte"),
                        new SaldoDisponible(SaldoDisponible.inicializarSaldo().value()),
                        UsuarioId.of("123"),
                        new EsAhorro(Boolean.FALSE),
                        new PorcentajeAhorro(10)));*/

        CrearBolsillo command = new CrearBolsillo(
                BolsilloId.of("xxxx"),
                new Nombre("Transporte"),
                UsuarioId.of("123"),
                new PorcentajeAhorro(10)
        );



        CrearBolsilloUseCase.Response events = executedUseCase(command);


        Assertions.assertEquals("xxxxx", events.getResponse().identity().value());
        Assertions.assertEquals("Transporte",events.getResponse().getNombre().value());
        Assertions.assertEquals(0, events.getResponse().getSaldoDisponible().value());
        Assertions.assertEquals(Boolean.FALSE,events.getResponse().getEsAhorro().value());
        Assertions.assertEquals("123",events.getResponse().getUid().value());
        Assertions.assertEquals(10, events.getResponse().getPorcentajeAhorro().value());
    }

    private CrearBolsilloUseCase.Response executedUseCase(CrearBolsillo command){
        CrearBolsilloUseCase.Response event = UseCaseHandler.getInstance()
                .syncExecutor(crearBolsilloUseCase, new RequestCommand<>(command))
                .orElseThrow();
        return event;
    }

}
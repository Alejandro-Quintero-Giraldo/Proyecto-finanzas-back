package co.com.finanzas.useCase;

import co.com.finanzas.domain.model.bolsillo.comands.CrearUsuario;
import co.com.finanzas.domain.model.bolsillo.events.UsuarioCreado;
import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class CrearUsuarioUseCaseTest {
    CrearUsuarioUseCase crearUsuarioUseCase;

    @BeforeEach
    public void setUp() {
         crearUsuarioUseCase = new CrearUsuarioUseCase();
    }

    @Test
    void crearUnUsuario(){
        CrearUsuario command = new CrearUsuario(
                UsuarioId.of("123"),
                new Nombre("Rafael Osorio"),
                new Email("rafosorio@gmail.com")
        );

        UsuarioCreado event = new UsuarioCreado(
                command.getUid(),
                command.getNombre(),
                command.getEmail()
        );
/*
        StepVerifier
                .create(crearUsuarioUseCase.apply(command))
                .expectNextMatches(domainEvent -> domainEvent.equals(event))
                .expectComplete();*/
    }
}
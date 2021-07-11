package co.com.finanzas.domain.infra.controller;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.UsuarioData;
import co.com.finanzas.domain.model.bolsillo.comands.CrearUsuario;
import co.com.finanzas.domain.model.bolsillo.values.Email;
import co.com.finanzas.domain.model.bolsillo.values.Nombre;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import co.com.finanzas.useCase.CrearUsuarioUseCase;
import co.com.finanzas.useCase.TransformacionBolsilloUseCase;
import co.com.finanzas.useCase.TransformacionUsuarioUseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private CrearUsuarioUseCase crearUsuarioUseCase;

    @Autowired
    private TransformacionUsuarioUseCase transformacionUsuarioUseCase;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

    @PostMapping(value = "api/crearUsuario/{uid}/{nombre}/{email}")
    public String saveUsuario(
            @PathVariable("uid")String uid,
            @PathVariable("nombre")String nombre,
            @PathVariable("email")String email){

        CrearUsuario command = new CrearUsuario(
                UsuarioId.of(uid),
                new Nombre(nombre),
                new Email(email)
        );

        CrearUsuarioUseCase.Response usuarioCreado = executedCrearUsuarioUseCase(command);

        String string = "{" + "\"UsuarioId\":"+ "\""+usuarioCreado.getResponse().identity().value()+"\""+","
                +"\"Nombre\":"+ "\""+usuarioCreado.getResponse().getNombre().value()+"\""+","
                +"\"Email\":"+ "\""+usuarioCreado.getResponse().getEmail().value()+"\""
                +"}";

        return string;
    }

    private CrearUsuarioUseCase.Response executedCrearUsuarioUseCase(CrearUsuario command){
        CrearUsuarioUseCase.Response events = UseCaseHandler.getInstance()
                .syncExecutor(crearUsuarioUseCase, new RequestCommand<>(command)).orElseThrow();

        CrearUsuarioUseCase.Response usuarioCreado = events;
        return usuarioCreado;
    }

    @GetMapping(value = "api/mostrarUsuarios")
    public Iterable<UsuarioData> listar(){
        return transformacionUsuarioUseCase.listar();
    }

    @GetMapping(value = "api/mostrarUsuario/{id}")
    public UsuarioData listarPorId(@PathVariable("id") String id){
        return transformacionUsuarioUseCase.listarPorId(id);
    }

    @GetMapping(value = "api/mostrarBolsilloUid/{uid}")
    public Iterable<BolsilloData> listarBolsillos(@PathVariable("uid") String uid){
        return transformacionBolsilloUseCase.listarPorUid(uid);
    }
}

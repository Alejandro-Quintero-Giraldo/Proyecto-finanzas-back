package co.com.finanzas.domain.infra.controller;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import co.com.finanzas.domain.model.bolsillo.comands.*;
import co.com.finanzas.domain.model.bolsillo.values.*;
import co.com.finanzas.useCase.*;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class BolsilloController {

    @Autowired
    private CrearBolsilloUseCase crearBolsilloUseCase;

    @Autowired
    private ActualizarBolsilloUseCase actualizarBolsilloUseCase;

    @Autowired
    private TransformacionBolsilloUseCase transformacionBolsilloUseCase;

    @Autowired
    private IngresarDineroUseCase ingresarDineroUseCase;

    @Autowired
    private TransformacionMovimientoUseCase transformacionMovimientoUseCase;

    @Autowired
    private SacarDineroUseCase sacarDineroUseCase;

    @PostMapping(value = "api/crearbolsillo/{bolsilloId}/{nombre}/{uid}/{porcentajeAhorro}")
    public String saveBolsillo(
            @PathVariable("bolsilloId")String bolsilloId,
            @PathVariable("nombre")String nombre,
            @PathVariable("uid")String uid,
            @PathVariable("porcentajeAhorro")Integer porcentajeAhorro){

        CrearBolsillo command = new CrearBolsillo(
                BolsilloId.of(bolsilloId),
                new Nombre(nombre),
                UsuarioId.of(uid),
                new PorcentajeAhorro(porcentajeAhorro));

        CrearBolsilloUseCase.Response bolsilloCreado = executedCrearBolsilloUseCase(command);
        String string = "{" + "\"BolsilloId\":"+ "\""+bolsilloCreado.getResponse().getIdPro()+"\""+","
                +"\"Nombre\":"+"\""+bolsilloCreado.getResponse().getNombre().value()+"\""+","
                +"\"Saldo disponible\":"+"\""+bolsilloCreado.getResponse().getSaldoDisponible().value()+"\""+","
                +"\"UsuarioId\":"+"\""+bolsilloCreado.getResponse().getUid().value()+"\""+","
                +"\"¿Es Ahorro?\":"+"\""+bolsilloCreado.getResponse().getEsAhorro().value()+"\""+","
                +"\"Porcentaje de ahorro\":"+"\""+bolsilloCreado.getResponse().getPorcentajeAhorro().value()+ "\""
                +"}";

        return string;
    }

    private CrearBolsilloUseCase.Response executedCrearBolsilloUseCase(CrearBolsillo command){
        CrearBolsilloUseCase.Response events = UseCaseHandler.getInstance()
                .syncExecutor(crearBolsilloUseCase,new RequestCommand<>(command)).orElseThrow();
        CrearBolsilloUseCase.Response bolsilloCreado = events;
        return bolsilloCreado;
    }

    @PutMapping(value = "api/actualizarBolsillo/{id}/{nombre}/{uid}/{porcentajeAhorro}")
    public String actualizar(@PathVariable("id") String id,
                             @PathVariable("nombre") String nombre,
                             @PathVariable("uid") String uid,
                             @PathVariable("porcentajeAhorro") Integer porcentajeAhorro){
        ActualizarBolsillo command = new ActualizarBolsillo(BolsilloId.of(id), new Nombre(nombre), UsuarioId.of(uid), new PorcentajeAhorro(porcentajeAhorro));
        ActualizarBolsilloUseCase.Response bolsilloActualizado = executedActualizarBolsilloUseCase(command);

        String string = "{" + "\"BolsilloId\":"+ "\""+bolsilloActualizado.getResponse().getId()+"\""+","
                +"\"Nombre\":"+ "\""+bolsilloActualizado.getResponse().getNombre()+"\""+","
                +"\"Saldo disponible\":"+"\""+bolsilloActualizado.getResponse().getSaldoDisponible()+"\""+","
                +"\"UsuarioId\":"+"\""+bolsilloActualizado.getResponse().getUid()+"\""+","
                +"\"¿Es Ahorro?\":"+"\""+bolsilloActualizado.getResponse().getEsAhorro()+"\""+","
                +"\"Porcentaje de ahorro\":"+"\""+bolsilloActualizado.getResponse().getPorcentajeAhorro()+"\"" +"}";

        return string;
    }

    public  ActualizarBolsilloUseCase.Response executedActualizarBolsilloUseCase(ActualizarBolsillo command){
        ActualizarBolsilloUseCase.Response  events = UseCaseHandler.getInstance()
                .syncExecutor(actualizarBolsilloUseCase, new RequestCommand<>(command)).orElseThrow();
        ActualizarBolsilloUseCase.Response  bolsilloActualizado = events;
        return  bolsilloActualizado;
    }

    @GetMapping(value = "api/mostrarBolsillos")
    public Iterable<BolsilloData> listar(){
        return (transformacionBolsilloUseCase.listar());
    }

    @GetMapping(value = "api/mostrarBolsillo/{id}")
    public BolsilloData listarPorId(@PathVariable("id") String id){
        return (transformacionBolsilloUseCase.listarPorId(id));
    }

    @DeleteMapping(value = "api/eliminarBolsillo/{id}")
    public String eliminar(@PathVariable("id") String id){
        return (transformacionBolsilloUseCase.eliminar(id));
    }

    @PostMapping(value = "api/ingresarDinero/{movimientoId}/{saldo}/{bolsilloId}/{uid}")
    public String saveIngresarDinero(
            @PathVariable("movimientoId") String movimientoId,
            @PathVariable("saldo") Integer saldo,
            @PathVariable("bolsilloId") String bolsilloId,
            @PathVariable("uid") String uid) {

        IngresarDinero command = new IngresarDinero(MovimientoId.of(movimientoId), new Saldo(saldo), BolsilloId.of(bolsilloId), UsuarioId.of(uid));

        IngresarDineroUseCase.Response dineroIngresado = executedIngresarDineroUseCase(command);

        String string = "{" + "\"MovimientoId\":" + "\"" + dineroIngresado.getResponse().identity().value() + "\"" + ","
                + "\"Saldo\":" + "\"" + dineroIngresado.getResponse().getSaldo().value() + "\"" + ","
                + "\"Tipo\":" + "\"" + dineroIngresado.getResponse().getTipo().value() + "\"" + ","
                + "\"Fecha\":" + "\"" + dineroIngresado.getResponse().getFecha().value() + "\"" + ","
                + "\"UsuarioId\":" + "\"" + dineroIngresado.getResponse().getUid().value() + "\"" + ","
                + "\"BolsilloId\":" + "\"" + dineroIngresado.getResponse().getBolsilloId().value() +"\"" + "}";
        return string;
    }

    public IngresarDineroUseCase.Response executedIngresarDineroUseCase(IngresarDinero command){

        IngresarDineroUseCase.Response dineroIngresado = UseCaseHandler.getInstance()
                .syncExecutor(ingresarDineroUseCase,new RequestCommand<>(command)).orElseThrow();
        return  dineroIngresado;
    }

    @GetMapping(value = "api/mostrarMovimientos/{bolsilloId}")
    public Iterable<MovimientoData> mostrarMovimientosPorBolsillo(@PathVariable("bolsilloId") String bolsilloId){
        return transformacionMovimientoUseCase.listarPorBolsilloId(bolsilloId);
    }

    @PostMapping(value = "api/sacarDinero/{movimientoId}/{saldo}/{bolsilloId}/{uid}")
    public String saveSacarDinero(
            @PathVariable("movimientoId") String movimientoId,
            @PathVariable("saldo") Integer saldo,
            @PathVariable("bolsilloId") String bolsilloId,
            @PathVariable("uid") String uid) {

        SacarDinero command = new SacarDinero(MovimientoId.of(movimientoId), new Saldo(saldo), BolsilloId.of(bolsilloId), UsuarioId.of(uid));

        SacarDineroUseCase.Response dineroSacado = executedSacarDineroUseCase(command);

        String string = "{" + "\"MovimientoId\":" + "\"" + dineroSacado.getResponse().identity().value() + "\"" + ","
                + "\"Saldo\":" + "\"" + dineroSacado.getResponse().getSaldo().value() + "\"" + ","
                + "\"Tipo\":" + "\"" + dineroSacado.getResponse().getTipo().value() + "\"" + ","
                + "\"Fecha\":" + "\"" + dineroSacado.getResponse().getFecha().value() + "\"" + ","
                + "\"UsuarioId\":" + "\"" + dineroSacado.getResponse().getUid().value() + "\"" + ","
                + "\"BolsilloId\":" + "\"" + dineroSacado.getResponse().getBolsilloId().value() + "\"" +"}";
        return string;
    }

    public SacarDineroUseCase.Response executedSacarDineroUseCase(SacarDinero command){
        SacarDineroUseCase.Response events = UseCaseHandler.getInstance()
                .syncExecutor(sacarDineroUseCase,new RequestCommand<>(command)).orElseThrow();

        SacarDineroUseCase.Response dineroSacado = events;
        return  dineroSacado;
    }

    @GetMapping(value ="api/ingresosMensuales/{bolsilloId}/{mes}/{año}")
    public Integer listarIngresosMensuales(
            @PathVariable("bolsilloId") String bolsilloId,
            @PathVariable("mes") String mes,
            @PathVariable("año") String año
            ){
        return transformacionMovimientoUseCase.listarIngresosMensuales(bolsilloId, mes, año);
    }

    @GetMapping(value = "api/egresosMensuales/{bolsilloId}/{mes}/{año}")
    public Integer listarEgresosMensuales(
            @PathVariable("bolsilloId") String bolsilloId,
            @PathVariable("mes") String mes,
            @PathVariable("año") String año
    ){
        return transformacionMovimientoUseCase.listarEgresosMensuales(bolsilloId, mes, año);
    }
}

package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.IMovimientoDataRepository;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransformacionMovimientoUseCase {

    @Autowired
    private IMovimientoDataRepository data;

    public Iterable<MovimientoData> listarPorBolsilloId(String bolsilloId){
        return data.findByBolsilloId(bolsilloId);
    }

    public String eliminarMovimientosPorBolsillo(Iterable<MovimientoData> movimientos){
        try {
            data.deleteAll(movimientos);
            return "Se eliminaron los movimientos del bolsillo con éxito";
        } catch (Exception e){
            return "Ocurrió un error. Por favor inténtelo nuevamente";
        }
    }

    public Integer listarIngresosMensuales(String bolsilloId, String mes, String año){
        Iterable<MovimientoData> movimientos = listarPorBolsilloId(bolsilloId);
        Integer ingresosMensuales = 0;

        for(MovimientoData movimiento: movimientos){
             if (movimiento.getFecha().getMonthValue() == Integer.parseInt(mes) && movimiento.getFecha().getYear() == Integer.parseInt(año) && movimiento.getTipo().equals("Ingreso")){

                 ingresosMensuales += movimiento.getSaldo();
             }
        }
        return  ingresosMensuales;
    }

    public Integer listarEgresosMensuales(String bolsilloId, String mes, String año){
        Iterable<MovimientoData> movimientos = listarPorBolsilloId(bolsilloId);
        Integer egresosMensuales = 0;

        for(MovimientoData movimiento: movimientos){
            if(movimiento.getFecha().getMonthValue() == Integer.parseInt(mes) && movimiento.getFecha().getYear() == Integer.parseInt(año) && movimiento.getTipo().equals("egreso")){
                egresosMensuales += movimiento.getSaldo();
            }
        }
        return  egresosMensuales;
    }
}

package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.BolsilloData;
import co.com.finanzas.domain.infra.repository.IBolsilloDataRepository;
import co.com.finanzas.domain.infra.repository.MovimientoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformacionBolsilloUseCase {

    @Autowired
    private IBolsilloDataRepository data;

    @Autowired
    private TransformacionMovimientoUseCase transformacionMovimientoUseCase;

    public Iterable<BolsilloData> listar(){
        return data.findAll();
    }

    public Iterable<BolsilloData> listarPorUid(String uid){
        return data.findByUid(uid);
    }

    public BolsilloData listarPorId(String id){
        return  data.findById(id).orElseThrow(RuntimeException::new);
    }

    public String eliminar(String id){
        BolsilloData bolsillo = listarPorId(id);
        if (bolsillo.getSaldoDisponible() == 0){
            try{
                Iterable<MovimientoData> movimientos = transformacionMovimientoUseCase.listarPorBolsilloId(id);
                transformacionMovimientoUseCase.eliminarMovimientosPorBolsillo(movimientos);
                data.deleteById(id);
                return  "Se eliminó el bolsillo con éxito";
            } catch (Exception e){
                return "No fue posible eliminar el bolsillo";
            }
        }else {
            return "No puedes eliminar un bolsillo si tienes saldo disponible. Por favor retire su saldo del bolsillo";
        }

    }
}

package co.com.finanzas.useCase;

import co.com.finanzas.domain.infra.repository.IUsuarioDataRepository;
import co.com.finanzas.domain.infra.repository.UsuarioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformacionUsuarioUseCase {
    @Autowired
    private IUsuarioDataRepository data;



    public Iterable<UsuarioData> listar(){
        return data.findAll();
    }

    public UsuarioData listarPorId(String id){
        return  data.findById(id).orElseThrow(RuntimeException::new);
    }

    public String eliminar(String id){
        try{
            data.deleteById(id);
            return  "Se eliminó el bolsillo con éxito";
        } catch (Exception e){
            return "No fue posible eliminar el bolsillo";
        }
    }
}

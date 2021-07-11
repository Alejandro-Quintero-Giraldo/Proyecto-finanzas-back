package co.com.finanzas.domain.infra.repository;

import co.com.finanzas.domain.model.bolsillo.Usuario;
import co.com.finanzas.domain.model.bolsillo.values.UsuarioId;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDataRepository extends CrudRepository<UsuarioData, String> {
}

package co.com.finanzas.domain.infra.repository;

import co.com.finanzas.domain.model.bolsillo.values.SaldoDisponible;
import org.springframework.data.repository.CrudRepository;

public interface IBolsilloDataRepository extends CrudRepository<BolsilloData, String> {
    Iterable<BolsilloData> findByUid(String uid);
}

package negozio.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import negozio.model.Prodotto;

@Repository
public interface ProdottoRepository extends CrudRepository<Prodotto, String> {

}

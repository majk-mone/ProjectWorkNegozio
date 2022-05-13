package negozio.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import negozio.model.Carrello;

@Repository
public interface CarrelloRepository extends CrudRepository<Carrello, String> {

	Carrello findByCodProdottoAndCodUtente(Integer codProdotto, Integer codUtente);

	List<Carrello> findByCodUtente(Integer codUtente);
}

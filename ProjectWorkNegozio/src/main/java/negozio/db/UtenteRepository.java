package negozio.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import negozio.model.Utente;

@Repository
public interface UtenteRepository extends CrudRepository<Utente, Integer> {
	List<Utente> findByUsername(String username);

	List<Utente> findByPassword(String password);

	Utente findByUsernameAndPassword(String username, String password);
}

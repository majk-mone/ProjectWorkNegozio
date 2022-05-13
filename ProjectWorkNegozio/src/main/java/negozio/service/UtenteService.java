package negozio.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import negozio.db.UtenteRepository;
import negozio.model.Utente;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepo;

	public String login(String username, String password, HttpServletRequest request, Model model) {

		Utente utente = utenteRepo.findByUsernameAndPassword(username, password);

		if (utente == null) {
			System.out.print("L'account non esiste");
			return "login";
		} else {
			request.getSession().setAttribute("utente", utente);
			System.out.print("L'account esiste");

			if (utente.getAdmin() == true) {
				return "admin";
			} else {
				model.addAttribute("utente", utente);
				System.out.println("UTENTE: " + request.getSession().getAttribute("utente"));

				return "home";
			}

		}

	}
}

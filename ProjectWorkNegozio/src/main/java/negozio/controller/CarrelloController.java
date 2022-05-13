package negozio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import negozio.db.CarrelloRepository;
import negozio.model.Carrello;
import negozio.model.Utente;

@Controller
public class CarrelloController {

	public static final String CARRELLO = "carrello";
	public static final String CARRELLI = "carrelli";

	@Autowired
	private CarrelloRepository carrelloRepo;

	@GetMapping("/visualizzaCarrello")
	public String visualizzaCarrello(Model model, HttpServletRequest request) {

		System.out.println("UTENTE: " + request.getSession().getAttribute("utente"));
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		Integer codUtente = utente.getCodUtente();

		Iterable<Carrello> carrelli = carrelloRepo.findByCodUtente(codUtente);
		model.addAttribute(CARRELLI, carrelli);
		return "visualizzaCarrello";
	}

	@GetMapping("/inserisciCarrello")
	public String inserisciCarrello(@RequestParam Integer codProdotto, Model model, HttpServletRequest request) {
		model.addAttribute(CARRELLI, new Carrello());

		Utente utente = (Utente) request.getSession().getAttribute("utente");
		Integer codUtente = utente.getCodUtente();

		System.out.println("COD PRODOTTO: " + codProdotto);
		System.out.println("COD UTENTE: " + codUtente);

		Carrello carrelloDoppione = carrelloRepo.findByCodProdottoAndCodUtente(codProdotto, codUtente);

		if (carrelloDoppione == null) {

			Carrello carrello = new Carrello();
			carrello.setCodProdotto(codProdotto);
			carrello.setCodUtente(codUtente);
			carrello.setQuantita(1);

			carrelloRepo.save(carrello);

		} else {

			carrelloDoppione.setQuantita(carrelloDoppione.getQuantita() + 1);
			carrelloRepo.save(carrelloDoppione);

		}

		return "redirect:visualizzaProdotti";
	}

	@PostMapping("/inserisciCarrello")
	public String inserisciCarrello(@Valid @ModelAttribute(CARRELLI) Carrello carrello, Errors errors, Model model) {

		carrelloRepo.save(carrello);
		model.addAttribute(CARRELLO, carrello);
		return "visualizzaProdotti";
	}

	@GetMapping("/eliminaCarrello")
	public String eliminaCarrello(@RequestParam Integer codProdotto, HttpServletRequest request) {

		Utente utente = (Utente) request.getSession().getAttribute("utente");
		Integer codUtente = utente.getCodUtente();

		Carrello carrelloDaEliminare = carrelloRepo.findByCodProdottoAndCodUtente(codProdotto, codUtente);

		if (carrelloDaEliminare != null) {
			carrelloRepo.delete(carrelloDaEliminare);
		}

		return "redirect:visualizzaCarrello";
	}

}
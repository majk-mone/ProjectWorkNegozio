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

import negozio.db.ProdottoRepository;
import negozio.model.Prodotto;
import negozio.model.Utente;

@Controller
public class ProdottoController {

	public static final String PRODOTTO = "prodotto";
	public static final String PRODOTTI = "prodotti";

	@Autowired
	private ProdottoRepository prodottoRepo;

	@GetMapping("/visualizzaProdotti")
	public String visualizzaProdotto(Model model, HttpServletRequest request) {

		System.out.println("UTENTE: " + request.getSession().getAttribute("utente"));

		Utente utente = (Utente) request.getSession().getAttribute("utente");
		model.addAttribute("utente", utente);

		Iterable<Prodotto> prodotti = prodottoRepo.findAll();
		model.addAttribute(PRODOTTI, prodotti);
		return "visualizzaProdotti";
	}

	@GetMapping("/inserisciProdotti")
	public String inserisciProdotto(Model model) {
		model.addAttribute(PRODOTTO, new Prodotto());
		return "inserisciProdotti";
	}

	@PostMapping("/inserisciProdotti")
	public String inserisciProdotto(@Valid @ModelAttribute(PRODOTTO) Prodotto prodotto, Errors errors, Model model) {

		if (errors.hasErrors()) {
			return "inserisciProdotti";
		}

		prodottoRepo.save(prodotto);
		model.addAttribute(PRODOTTO, prodotto);
		return "redirect:visualizzaProdotti";
	}
}
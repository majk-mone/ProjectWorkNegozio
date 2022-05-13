package negozio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import negozio.model.Utente;
import negozio.service.UtenteService;

@Controller
public class UtenteController {

	public static final String UTENTE = "utente";
	public static final String UTENTI = "utenti";

	@Autowired
	private UtenteService utenteService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUtente(@ModelAttribute("login") Utente utente, HttpServletRequest request, Model model) {
		System.out.println(utente.toString());

		return utenteService.login(utente.getUsername(), utente.getPassword(), request, model);
	}
}

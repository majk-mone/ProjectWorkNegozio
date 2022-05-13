package negozio.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import negozio.model.Utente;

@Controller
public class MainController {
	@GetMapping("/")
	public String inviaPortale() {
		return "portale";
	}

	@GetMapping("/admin")
	public String inviaAdmin() {
		return "admin";
	}

	@GetMapping("/home")
	public String inviaHome(HttpServletRequest request, Model model) {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		model.addAttribute("utente", utente);

		return "home";
	}

}

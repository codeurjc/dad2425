package es.codeurjc.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

	private UserService usersService;

	public GreetingController(UserService userService) {
		this.usersService = userService;
	}
	
	@GetMapping("/greeting")
	public String greeting(Model model) {

		model.addAttribute("name", usersService.getNumUsers() + " users");

		return "greeting_template";
	}

}

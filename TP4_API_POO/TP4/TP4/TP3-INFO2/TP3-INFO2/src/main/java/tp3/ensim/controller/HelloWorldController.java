package tp3.ensim.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tp3.ensim.model.AddressForm;

@Controller
public class HelloWorldController {
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="nameGET", required=false, defaultValue="World") String nameGET, Model model) {
		model.addAttribute("nomTemplate", nameGET);
		model.addAttribute("addressForm", new AddressForm(null));
		return "greeting";
	}
}
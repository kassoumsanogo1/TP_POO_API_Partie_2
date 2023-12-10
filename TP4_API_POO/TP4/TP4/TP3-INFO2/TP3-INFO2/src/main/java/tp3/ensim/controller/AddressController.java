package tp3.ensim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tp3.ensim.model.AddressForm;
import tp3.ensim.model.AdresseRepository;

@Controller
public class AddressController {

    @Autowired
    AdresseRepository addresseRepository;

    @GetMapping("/addresses")
    public String showAddresses(Model model) {
        model.addAttribute("allAddresses", addresseRepository.findAll());
        model.addAttribute("addressForm", new AddressForm(null));
        return "addresses";
    }
}

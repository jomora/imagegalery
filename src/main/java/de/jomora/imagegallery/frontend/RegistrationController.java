package de.jomora.imagegallery.frontend;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.jomora.imagegallery.backend.services.CustomerService;
import de.jomora.imagegallery.persistence.Authority;
import de.jomora.imagegallery.persistence.Customer;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {
	private Logger log = LoggerFactory.getLogger(RegistrationController.class);
	@Autowired
	private CustomerService service;

	@RequestMapping(method = RequestMethod.GET)
	public String registrationPage(Model model) {
		model.addAttribute("regdto", new RegistrationDTO());
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@ModelAttribute RegistrationDTO registrationDto, Model model) {
		log.info("user " + registrationDto.getUsername() + ", password " + registrationDto.getPassword());
		checkNotNull(registrationDto.getUsername());
		checkNotNull(registrationDto.getPassword());
		Authority auth = new Authority();
		auth.setAuthority("ROLE_USER");
		Customer customer = new Customer(registrationDto.getUsername(), registrationDto.getPassword(), true);

		customer.setAuthorities(new ArrayList<Authority>());
		customer.getAuthorities().add(auth);
		Customer tmpCustomer = service.addCustomer(customer);
		auth.setCustomer(tmpCustomer);
		service.addAuthorityOfCustomer(auth);
		return "login";
	}

}

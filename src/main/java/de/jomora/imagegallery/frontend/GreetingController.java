package de.jomora.imagegallery.frontend;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import de.jomora.imagegallery.backend.services.CustomerService;
import de.jomora.imagegallery.backend.services.ImageService;
import de.jomora.imagegallery.persistence.Authority;
import de.jomora.imagegallery.persistence.Customer;
import de.jomora.imagegallery.persistence.Image;

@Controller
public class GreetingController {
	private Logger log = LoggerFactory.getLogger(GreetingController.class);
	@Autowired
	private CustomerService service;

	@Autowired
	private ImageService imageService;

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);

		return "greeting";
	}

	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String gallery(Model model) {
		log.info("called gallery GET");
		List<Customer> users = service.findAll();
		model.addAttribute("users", users);
		String username = getCurrentPrincipalUsername();
		Customer customer = service.findByName(username);
		if (customer != null) {
			model.addAttribute("images", customer.getImages());
		}
		return "gallery";
	}

	private String getCurrentPrincipalUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = "";
		}
		log.info("Current principal's username: " + username);
		return username;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationPage(Model model) {
		model.addAttribute("regdto", new RegistrationDTO());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
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

	@RequestMapping(value = "/gallery", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		log.info("called gallery POST");
		Customer customer = service.findByName(getCurrentPrincipalUsername());
		try {
			String fileName = file.getOriginalFilename();

			byte[] bytes = file.getBytes();
			Image image = new Image();
			image.setCustomer(customer);
			image.setImage(bytes);
			image.setName(fileName);
			imageService.add(image);
			log.info("Stored image: " + image.toString());
		} catch (Exception e) {
		}
		List<Image> images = customer.getImages();
		model.addAttribute("images", images);
		return "gallery";
		// return "gallery :: imageRow";
	}

	// @RequestMapping("/gallery/search/{searchQuery}")
	// private String remoteSearchWithQuery(@PathVariable(value = "searchQuery")
	// String searchQuery, Model model) {
	// Customer customer = service.findByName("jonas");
	// model.addAttribute("images", customer.getImages());
	// List<Image> images = new ArrayList<>();
	// Pattern pattern = Pattern.compile(".*" + searchQuery + ".*");
	// for (Image image : customer.getImages()) {
	// if (pattern.matcher(image.getName()).matches()) {
	// images.add(image);
	// log.info("added " + image.getName());
	// }
	// }
	// log.info(searchQuery);
	// model.addAttribute("images", images);
	// return "imageRow :: resultsList";
	// }
	//
	// @RequestMapping("/gallery/search/")
	// private String remoteSearch(Model model) {
	// Customer customer = service.findByName("jonas");
	// model.addAttribute("images", customer.getImages());
	// return "imageRow :: resultsList";
	// }
}

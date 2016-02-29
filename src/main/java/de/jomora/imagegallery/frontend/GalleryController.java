package de.jomora.imagegallery.frontend;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import de.jomora.imagegallery.backend.services.CustomerService;
import de.jomora.imagegallery.backend.services.ImageService;
import de.jomora.imagegallery.persistence.Customer;
import de.jomora.imagegallery.persistence.Image;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {
	private Logger log = LoggerFactory.getLogger(GalleryController.class);

	@Autowired
	private CustomerService service;

	@Autowired
	private ImageService imageService;

	@RequestMapping(params = { "addImage" }, method = RequestMethod.POST)
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
		return "gallery :: imageRow";
		// return "gallery :: imageRow";
	}

	@RequestMapping(method = RequestMethod.GET)
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
}

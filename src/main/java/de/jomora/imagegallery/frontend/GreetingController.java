package de.jomora.imagegallery.frontend;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Guava;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import de.jomora.imagegallery.backend.services.CustomerService;
import de.jomora.imagegallery.backend.services.ImageService;
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

	@RequestMapping("/gallery")
	public String gallery(Model model) {
		List<Customer> users = service.findAll();
		model.addAttribute("users", users);

		Customer customer = service.findByName("asd");
		model.addAttribute("images", customer.getImages());
		return "gallery";
	}

	@RequestMapping("/register")
	public String register(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password) {
		log.info("user " + username + ", password " + password);
		checkNotNull(username);
		checkNotNull(password);

		Customer customer = new Customer(username, password, "user");
		service.addCustomer(customer);
		return "login";
	}

	@RequestMapping(value = "/gallery/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				Customer customer = service.findByName("asd");
				byte[] bytes = file.getBytes();
				Image image = new Image();
				image.setCustomer(customer);
				image.setImage(bytes);
				image.setName(name);
				imageService.add(image);
			} catch (Exception e) {
			}
		}
		return "gallery :: imageRow";
	}

	@RequestMapping("/gallery/showimage")
	private ResponseEntity<byte[]> showImage() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		Customer customer = service.findByName("asd");
		return new ResponseEntity<byte[]>(
				customer.getImages().isEmpty() ? new byte[0] : customer.getImages().get(0).getImage(),
				HttpStatus.CREATED);
	}

	@RequestMapping("/gallery/showimage2")
	private String showImage2(Model model) {
		Customer customer = service.findByName("asd");
		// List<Image> images = customer.getImages();
		// model.addAttribute("images",images);
		// log.info("image slength: " + images.size());

		List<String> images = new ArrayList<>();
		for (Image i : customer.getImages()) {
			images.add(new String(Base64.encodeBase64(i.getImage())));
		}
		return "gallery";
	}

	@RequestMapping("/gallery/search/{searchQuery}")
	private String remoteSearchWithQuery(@PathVariable(value = "searchQuery") String searchQuery, Model model) {
		Customer customer = service.findByName("asd");
		model.addAttribute("images", customer.getImages());
		List<Image> images = new ArrayList<>();
		Pattern pattern = Pattern.compile(".*" + searchQuery + ".*");
		for (Image image : customer.getImages()) {
			if (pattern.matcher(image.getName()).matches()) {
				images.add(image);
				log.info("added " + image.getName());
			}
		}
		log.info(searchQuery);
		model.addAttribute("images", images);
		return "imageRow :: resultsList";
	}

	@RequestMapping("/gallery/search/")
	private String remoteSearch(Model model) {
		Customer customer = service.findByName("asd");
		model.addAttribute("images", customer.getImages());
		return "imageRow :: resultsList";
	}

}

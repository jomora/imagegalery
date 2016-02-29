package de.jomora.imagegallery.frontend;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/login")
	public String login(Model model){
		log.info("called login() in LoginController.class");
		model.addAttribute("logoutMessage",model.asMap().get("logoutMessage"));
		return "login";
	}
	
	@RequestMapping("/login-failed")
	public String loginFailed(Model model){
		log.info("called loginFailed() in LoginController.class");
		model.addAttribute("errorMessage","Could not log in. Are you're credentials correct?");
		return "login";
	}
	
}

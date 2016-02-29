package de.jomora.imagegallery.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	private static Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping
	@ResponseBody
	public TestDto test(TestDto dto) {
		log.info(dto.toString());
		return dto;
	}

	@RequestMapping("/entity")
	public String entityTest(@ModelAttribute TestDto dto,Model model, TestDto form){
		log.info("after request: " + dto.toString());
		dto.setNase(form.getNase() + " test");
		model.addAttribute("dto",dto);
		return "test";
	}
	// @RequestMapping(method = RequestMethod.POST)
	// public String specificTest(@RequestBody String body,Writer writer){
	// log.info(body);
	//
	// return "test";
	// }
}

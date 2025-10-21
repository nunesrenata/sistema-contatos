package br.com.renata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value ="/agenda/home")
	public ModelAndView home() {
		
		ModelAndView modelAndView = new ModelAndView("agenda/home");
		return modelAndView;
		
	}
	
}

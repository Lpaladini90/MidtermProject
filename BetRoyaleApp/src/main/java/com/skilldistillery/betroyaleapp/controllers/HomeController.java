package com.skilldistillery.betroyaleapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
		
	@RequestMapping(path = { "/", "splash.do" })
	public String home() {
		return "splashPage";
	}
	
}

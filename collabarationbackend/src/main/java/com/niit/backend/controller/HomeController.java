package com.niit.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public ModelAndView loadingPage() {
		System.out.println("in home controller");
		return new ModelAndView("index");
	}
	
}

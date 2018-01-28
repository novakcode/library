package com.librarysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping({"/","/library-home"})
	public String libaryHomePage()
	{
	
		return "library-home";
	}
}

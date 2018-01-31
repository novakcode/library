package com.librarysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.librarysystem.security.IAuthenticationFacade;

@Controller
public class SecurityController {
		
	private IAuthenticationFacade authenticationFacade;
	
	@GetMapping("/user")
	public String getUser()
	{
		return authenticationFacade.getAuthentication().getName();
	}
	
}

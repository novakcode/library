package com.librarysystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.librarysystem.entity.Member;
import com.librarysystem.service.BookService;
import com.librarysystem.service.LoanedBookService;
import com.librarysystem.service.MemberService;

@Controller
public class MainController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private LoanedBookService loanedBookService;
	
	
	@GetMapping({"/","/library-home"})
	public String libaryHomePage()
	{
	
		return "library-home";
	}
	
	@GetMapping("/library-login")
	public String libraryLoginPage()
	{

		
		return "library-login";
	}
}

package com.librarysystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.librarysystem.entity.Book;
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
	
	
	
	/* LIBRARY HOME AND LOGIN PAGE MAPPING */
	@GetMapping({"/","/library-home"})
	public String libaryHomePage()
	{
	
		return "library/library-home";
	}
	
	@GetMapping("/library-login")
	public String libraryLoginPage()
	{

		
		return "library/library-login";
	}
	
	
	//	Book pages mapping
	
	
	/* FIND BOOK */	 /* FIND BOOK */ 	/* FIND BOOK */ 
	
	@GetMapping("/book-find")
	public String bookFindPage()
	{
		return "book/book-find";
	}
	
	
	@PostMapping("/book-find")
	public String findBook(@RequestParam("searchField")String searchField,@RequestParam("searchType")String searchType
			,Model model)
	{
		
	
		return "redirect:/book-find";
	}
	
	
	
	/* LOAN BOOK */ /* LOAN BOOK */ /* LOAN BOOK */ 
	
	
	@GetMapping("/book-loan")
	public String bookLoanPage()
	{
		return "book/book-loan";
	}
	
	
	/* REGISTER BOOK */	/* REGISTER BOOK */	/* REGISTER BOOK */
	
	@GetMapping("/book-register")
	public String bookRegisterPage()
	{
		return "book/book-register";
	}
	
	
	@PostMapping("/book-register")
	public  String registerBook(@Valid Book book,BindingResult result)
	{
		
		
		
		
		return "redirect:/book-register";
	}
	
	
	
	
	// Member mapping
	
	
	/* FIND MEMBER */ /* FIND MEMBER */ /* FIND MEMBER */ 
	
	@GetMapping("/member-find")
	public String memberFindPage()
	{
		return "member/member-find";
	}
	
	/* REGISTER MEMBER */ /* REGISTER MEMBER */ /* REGISTER MEMBER */
	@GetMapping("/member-register")
	public String memberRegisterPage()
	{
		return "member/member-register";
	}
	
	
	/* RENEW MEMBER */ /* RENEW MEMBER */ /* RENEW MEMBER */ 
	@GetMapping("/member-renew")
	public String memberRenewPage()
	{
		return "member/member-renew";
	}
	
	
}

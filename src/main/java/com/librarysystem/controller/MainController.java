package com.librarysystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.librarysystem.entity.Book;
import com.librarysystem.service.BookService;
import com.librarysystem.service.LoanedBookService;
import com.librarysystem.service.MemberService;
// Git commit test
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
	
	// Book mapping
	
	@GetMapping("/book-find")
	public String bookFindPage()
	{
		return "book-find";
	}
	
	@GetMapping("/book-loan")
	public String bookLoanPage()
	{
		return "book-loan";
	}
	
	@GetMapping("/book-register")
	public String bookRegisterPage()
	{
		return "book-register";
	}
	
	// Post register
	@PostMapping("/book-register")
	public  String registerBook(@Valid Book book,BindingResult result)
	{
		
		
		return "book-register";
	}
	
	
	// Member mapping
	
	@GetMapping("/member-find")
	public String memberFindPage()
	{
		return "member-find";
	}
	@GetMapping("/member-register")
	public String memberRegisterPage()
	{
		return "member-register";
	}
	
	@GetMapping("/member-renew")
	public String memberRenewPage()
	{
		return "member-renew";
	}
	
	
}

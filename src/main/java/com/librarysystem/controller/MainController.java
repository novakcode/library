package com.librarysystem.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	
	
	
	private static final int PAGE_SIZE  = 5;

	
	
	
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
	
	@GetMapping("/book-find-by-isbn")
	public String findBookByIsbn(@RequestParam("isbn")String isbn
			,Model model)
	{
		
			Book book = bookService.findBookByIsbn(isbn);
			if(book == null)
			{
				return "redirect:/book-find";
			}
			model.addAttribute("book", book);
			
		return "book/book";
	}

	
	@GetMapping("/book-find-by-author")
	public String findBookByAuthor(@RequestParam("page")Optional<Integer> page,
			@RequestParam("author")String author
			,Model model)
	{
		
		
		

		
		
	
		return "redirect:/bookList";
	}
	
	@GetMapping("/book-find-by-title")
	public String findBookByTitle(@RequestParam("page")Optional<Integer> page,
			@RequestParam("title")String title
			,Model model)
	{
		
		
		

		
		
	
		return "redirect:/bookList";
	}
	
	
	@GetMapping("/bookList")
	public String getBookList()
	{
		
		return "book/bookList";
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
	public  String registerBook(@Valid Book book,BindingResult result,RedirectAttributes attributes)
	{
		
		if(result.hasErrors())
		{
			
			return "redirect:/book-register";
		}
			
		bookService.createOrUpdateBook(book);
		
		
		return "redirect:/book-find-by-isbn?isbn="+book.getIsbn();
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

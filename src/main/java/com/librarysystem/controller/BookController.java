package com.librarysystem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.librarysystem.entity.Book;
import com.librarysystem.entity.Member;
import com.librarysystem.model.Pager;
import com.librarysystem.service.BookService;
import com.librarysystem.service.LoanedBookService;
import com.librarysystem.service.MemberService;



@Controller
public class BookController {
	
	
	
	// Constants for pagging
	private static final int PAGE_SIZE = 5;
	private static final int NAVIGATION_PAGE_SIZE = 5;
	
	@Autowired
	private BookService bookService;
	

	@Autowired
	private LoanedBookService loanedBookService;
	
	@Autowired
	private MemberService memberService;


	

	@GetMapping("/book-find")
	public String bookFindPage() {		
		
		return "book/book-find";
	}
	
	/*
	 * Searching for book using Isbn
	 */

	@GetMapping("/find-book-by-isbn")
	public String findBookByIsbn(@RequestParam("isbn") String isbn, Model model) {
		
		Book book = bookService.findBookByIsbn(isbn);
		if (book == null) {
			return "book/book-find";
		}
		
		model.addAttribute("book", book);

		return "book/book";
	}
	
	
	
	/*
	 * Searching for book using Author name.
	 */

	@GetMapping("/find-book-by-author")
	public String findBookByAuthor(@RequestParam(name = "page", defaultValue = "1")Optional<Integer>page,
			@RequestParam(name="author",defaultValue="") String author, Model model) {

	
			
			
			Page<Book> bookList = bookService.findBookByAuthor(author, PageRequest.of(page.get() - 1, PAGE_SIZE));
			
			if(bookList != null && bookList.hasContent()){
				
				Pager pager = new Pager(NAVIGATION_PAGE_SIZE,page.get(),bookList.getTotalPages());
				
				model.addAttribute("field", author);
				model.addAttribute("books",bookList.getContent());
				model.addAttribute("pages", pager.getNavigationPages());
				
				return "book/bookList";
				
			}
			
			
			
			
			return "book/book-find";
		

	
	}
	
	/*
	 * Searching for book using Title.
	 */

	@GetMapping("/find-book-by-title")
	public String findBookByTitle(@RequestParam(name="page",defaultValue = "1") Optional<Integer> page,
			@RequestParam(name="title",defaultValue="") String title,
			Model model) {
		
	
		
		Page<Book> bookList = bookService.findBookByTitle(title, PageRequest.of(page.get() - 1,PAGE_SIZE));
		
		if(bookList != null && bookList.hasContent())
		{
			Pager pager = new Pager(NAVIGATION_PAGE_SIZE,page.get(),bookList.getTotalPages());
			
			model.addAttribute("field", title);
			model.addAttribute("books", bookList.getContent());
			model.addAttribute("pages",pager.getNavigationPages());
			return "book/bookList";
		}
		

		return "book/book-find";
	}

	/* LOAN BOOK */ /* LOAN BOOK */ /* LOAN BOOK */
	
	@GetMapping("/book-loan")
	public String bookLoanPage() {
		return "book/book-loan";
	}
	
	
	/* 
	 * Loans list of books(or a single book) to a member with card id.
	 */
	@PostMapping("/loan-book")
	public String loanBook(@RequestParam("cardId") int cardId,@RequestParam List<String> isbn){
		
		Member member = memberService.findMemberByCardId(cardId);
		
		
		
		if(member == null){
			return "redirect:/book-loan";
		}
		

		
		loanedBookService.loanBooks(isbn, member);
		
		
	
		return "redirect:/book-loan";
	}
	

	/* REGISTER BOOK */ /* REGISTER BOOK */ /* REGISTER BOOK */

	@GetMapping("/book-register")
	public String bookRegisterPage() {

		return "book/book-register";
	}

	
	/*
	 *	 Registers book.
	 */
	
	@PostMapping("/register-book")
	public String registerBook(@Valid @ModelAttribute("newBook") Book newBook, BindingResult result) {
		if (result.hasErrors()) {
			
			return "redirect:/book-register";
		}

		bookService.createOrUpdateBook(newBook);

		return "redirect:/find-book-by-isbn?isbn=" + newBook.getIsbn();
	}
		
	

}

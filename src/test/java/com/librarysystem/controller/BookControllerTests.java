package com.librarysystem.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.librarysystem.SpringBootWebApplication;
import com.librarysystem.entity.Book;
import com.librarysystem.entity.Member;
import com.librarysystem.model.Pager;
import com.librarysystem.service.BookService;
import com.librarysystem.service.LoanedBookService;
import com.librarysystem.service.MemberService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringBootWebApplication.class})
@WebAppConfiguration
public class BookControllerTests {
	
	private static final Book book =  new Book("414-5-215156-56-1","Title","Author",true,"A09");
	private static final List<Book> books = Collections.singletonList(book);
	private static final Pager pager =  new Pager(1,1,1);
	private static final Member member = new Member(1,"First Second","Adress","0600000");
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mvc;

	
	@MockBean
	private BookService bookService;

	
	@MockBean
	private MemberService memberService;
	
	@MockBean
	private LoanedBookService loanedBookService;
	
	
	@Before
	public void setUp(){
		
		Pageable pageable = PageRequest.of(0, 5);
		Page<Book> page = new PageImpl<Book>(books,pageable,1);

		
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
		when(bookService.findBookByIsbn(book.getIsbn())).thenReturn(book);
		when(bookService.findBookByAuthor(book.getAuthor(), pageable)).thenReturn(page);
		when(bookService.findBookByTitle(book.getTitle(), pageable)).thenReturn(page);
		when(bookService.createOrUpdateBook(book)).thenReturn(book);
		when(memberService.findMemberByCardId(1)).thenReturn(member);

	}
	
	@Test
	public void findBookByIsbn_IfIsbnValid() throws Exception{
		mvc.perform(get("/find-book-by-isbn").param("isbn", "414-5-215156-56-1")).andExpect(status().isOk())
		.andExpect(view().name("book/book"))
		.andExpect(model().attribute("book",book));
	
	}
	

	
	@Test
	public void findBookByAuthor_IfAuthorExist() throws Exception{
	
		mvc.perform(get("/find-book-by-author").param("author", book.getAuthor())).andExpect(status().isOk())
		.andExpect(view().name("book/bookList"))
		.andExpect(model().attribute("pages", pager.getNavigationPages()))
		.andExpect(model().attribute("books",books))
		.andExpect(model().attribute("field", book.getAuthor()));
	}
	

	
	@Test
	public void findBookByTitle_IfTitleExist() throws Exception{
		mvc.perform(get("/find-book-by-title").param("title", book.getTitle())).andExpect(status().isOk())
		.andExpect(view().name("book/bookList"))
		.andExpect(model().attribute("pages", pager.getNavigationPages()))
		.andExpect(model().attribute("books", books))
		.andExpect(model().attribute("field",book.getTitle()));
	}
	
	@Test
	public void registerValidBook() throws Exception{
		mvc.perform(post("/register-book").param("isbn", book.getIsbn())
				.param("title", book.getTitle())
				.param("author", book.getAuthor())
				.param("available", "true")
				.param("section", book.getSection())).andExpect(status().isFound())
				.andExpect(view().name("redirect:/find-book-by-isbn?isbn="+book.getIsbn()));
		verify(bookService,times(1)).createOrUpdateBook(any(Book.class));
	}
	
	
	@Test
	public void registerInvalidBook() throws Exception{
		mvc.perform(post("/register-book").param("isbn", "badisbn"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/book-register"));
		
		verify(bookService,times(0)).createOrUpdateBook(any(Book.class));
	}
	
	
	
	@Test
	public void loanBooksToMemberWithValidCardId() throws Exception{
		mvc.perform(post("/loan-book").param("cardId",member.getCardId()+"")
				.param("isbn", book.getIsbn()))
			.andExpect(status().isFound())
			.andExpect(view().name("redirect:/book-loan"));
		
		verify(memberService,times(1)).findMemberByCardId(member.getCardId());
		verify(loanedBookService,times(1)).loanBooks(anyList(), any(Member.class));
		verifyNoMoreInteractions(memberService,loanedBookService);
	}
	
	@Test
	public void loanBooksToMemberWithInvalidCardId() throws Exception{
		mvc.perform(post("/loan-book").param("cardId", "2").param("isbn", book.getIsbn()))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/book-loan"));
		
		verify(memberService,times(1)).findMemberByCardId(2);
		verifyNoMoreInteractions(memberService);
		verify(loanedBookService,times(0)).loanBooks(anyList(), any(Member.class));
	}
	
	@Test
	public void findBookByTitle_IfTitleNotExist() throws Exception{
		notExist("/find-book-by-title","title");
	}
	
	@Test
	public void findBookByIsbn_IfIsbnInvalid() throws Exception{
		notExist("/find-book-by-isbn","isbn");
	}
	
	@Test
	public void findBookByAuthor_IfAuthorNotExist() throws Exception{
		notExist("/find-book-by-author","author");
	}

	
	private void notExist(String url,String field) throws Exception{
		mvc.perform(get(url).param(field, "")).andExpect(status().isOk())
		.andExpect(view().name("book/book-find"));
	}
	
	

	
	
}

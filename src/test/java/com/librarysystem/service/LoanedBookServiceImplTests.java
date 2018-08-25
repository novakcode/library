package com.librarysystem.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.librarysystem.entity.Book;
import com.librarysystem.entity.LoanedBook;
import com.librarysystem.entity.Member;
import com.librarysystem.repository.BookRepository;
import com.librarysystem.repository.LoanedBookRepository;

@RunWith(SpringRunner.class)
public class LoanedBookServiceImplTests {

	
	private static final Member member = new Member();
	
	@TestConfiguration
	static class LoanedBookServiceImplTestContextConfiguration{
		
		
		@Bean
		public LoanedBookService loanedBookService(){
			return new LoanedBookServiceImpl();
		}
	}
	
	@Autowired
	private LoanedBookService loanedBookService;
	
	@MockBean
	public LoanedBookRepository loanedBookRepository;

	
	@MockBean
	public BookRepository bookRepository;
	
	@Before
	public void setUp(){
		List<LoanedBook> loanedBooks  = new ArrayList<LoanedBook>();
		
		
		member.setCardId(1);
		
		Book book =  new Book();
		book.setAvailable(true);
		
		when(loanedBookRepository.findLoanedBookByMember_CardId(any(Integer.class))).thenReturn(loanedBooks);
		when(bookRepository.findBookByIsbn(any(String.class))).thenReturn(book);
	}
	
	@Test
	public void returnLoanedBooksByIdIfExist(){
		List<LoanedBook> loanedBooks  = new ArrayList<LoanedBook>();
		List<LoanedBook> loanedBooksFound = loanedBookService.findLoanedBookByCardId(1);
	
		verify(loanedBookRepository,times(1)).findLoanedBookByMember_CardId(1);
		verifyNoMoreInteractions(loanedBookRepository);
		
		assertEquals(loanedBooks.size(),loanedBooksFound.size());
	}
	
	@Test
	public void returnBookByCardId_DeletesLoanedBooks(){
		loanedBookService.returnBooks(member);
		
		verify(loanedBookRepository,times(1)).deleteLoanedBookByMember_CardId(member);
		verifyNoMoreInteractions(loanedBookRepository);
		
		
	}
	
	@Test
	public void saveLoanedBooks(){
		List<String> booksToLoan = Arrays.asList("1","2","3");
		loanedBookService.loanBooks(booksToLoan, member);
		
		verify(bookRepository,times(3)).findBookByIsbn(any(String.class));
		verify(loanedBookRepository,times(3)).save(any(LoanedBook.class));
		
		verifyNoMoreInteractions(bookRepository);

		verifyNoMoreInteractions(loanedBookRepository);
		
	}
	
	
	
}

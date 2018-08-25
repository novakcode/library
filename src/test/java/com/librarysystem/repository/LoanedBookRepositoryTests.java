package com.librarysystem.repository;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.librarysystem.entity.Book;
import com.librarysystem.entity.LoanedBook;
import com.librarysystem.entity.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanedBookRepositoryTests {

	private static final Book BOOK = new Book("414-5-215156-56-1","Title", "Author", false,"A07");
	private static final Member MEMBER = new Member(1,"Example Example","Adress","02141441");
	
	@Autowired
	private LoanedBookRepository repository;


	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Before
	public void setUp(){
		bookRepository.save(BOOK);
		memberRepository.save(MEMBER);
		
	}
	
	@Test
	public void findLoanedBookByCardIdReturnsBooks(){	
		
		LoanedBook loanedBook = new LoanedBook(MEMBER,BOOK,LocalDate.now(),LocalDate.now().plusMonths(1));
		loanedBook.setLoanId(1);
		repository.save(loanedBook);
		
		List<LoanedBook> loanedBooks = new ArrayList<LoanedBook>();
		loanedBooks.add(loanedBook);
		
		List<LoanedBook> found = repository.findLoanedBookByMember_CardId(1);
		
		assertEquals(loanedBooks.size(), found.size());
	}
	
	@Test
	@Transactional
	public void deleteLoanedBooksByMember(){
	
		assertEquals(1,repository.count());
		
		repository.deleteLoanedBookByMember_CardId(MEMBER);
			
		assertEquals(0,repository.count());
	}
	

}

package com.librarysystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.Book;
import com.librarysystem.entity.LoanedBook;
import com.librarysystem.entity.Member;
import com.librarysystem.repository.BookRepository;
import com.librarysystem.repository.LoanedBookRepository;

@Service("loanedBookService")
public class LoanedBookServiceImpl implements LoanedBookService {

	private static final Logger logger = LoggerFactory.getLogger(LoanedBookServiceImpl.class);

	@Autowired
	private LoanedBookRepository loanedBookRepository;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<LoanedBook> findLoanedBookByCardId(int cardId) {
		logger.debug("Finding Book by cardId:{}", cardId);
		return loanedBookRepository.findLoanedBookByMember_CardId(cardId);
	}

	@Override
	public void loanBooks(List<String> isbnToLoan, Member member) {

		
		List<Book> booksToLoan = new ArrayList<Book>();

		isbnToLoan.stream().forEach(isbn -> {
			Book book = bookRepository.findBookByIsbn(isbn);
			if (book != null && book.isAvailable()) {
				logger.debug("Book:{}", book);
				booksToLoan.add(book);
				book.setAvailable(false);
				bookRepository.save(book);
			}
		});
		

		LocalDate dateOut = LocalDate.now();
		LocalDate dateDue = dateOut.plusMonths(1);

		booksToLoan.stream().forEach(book -> {
			
			LoanedBook loanedBook = new LoanedBook(member, book, dateOut, dateDue);
			logger.debug("Loaned Book:{}",loanedBook);
			loanedBookRepository.save(loanedBook);
			
		});
	}

}

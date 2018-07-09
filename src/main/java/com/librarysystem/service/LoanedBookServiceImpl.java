package com.librarysystem.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 
	 * This method finds every book that is gonna be loaned by isbn. Then creates and
	 * persists books to LoanedBook table.
	 * 
	 * 
	 **/
	@Override
	public void loanBooks(List<String> isbnToLoan, Member member) {

		logger.info("Getting books from repository...");

		LocalDate dateOut = LocalDate.now();
		LocalDate dateDue = dateOut.plusMonths(1);
		
		isbnToLoan.stream().forEach(isbn -> {
			Book book = bookRepository.findBookByIsbn(isbn);
			
			if (book != null && book.isAvailable()) {
				logger.debug("Book:{}", book);
				loanedBookRepository.save(new LoanedBook(member, book, dateOut, dateDue));
				
			}
		});
		
	}

	/**
	 * Returns every book assigned to certain member.
	 * 
	 * Deletes every row where member cardId = certain cardId
	 * 
	 **/

	@Override
	@Transactional
	public void returnBooks(Member member) {
		logger.debug("Returning books for cardId:{}", member.getCardId());

		logger.info("Setting book available field to true...");

		logger.info("Deleting loaned books...");
		loanedBookRepository.deleteLoanedBookByMember_CardId(member);

	}

}

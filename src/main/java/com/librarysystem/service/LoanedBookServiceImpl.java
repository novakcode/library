package com.librarysystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.LoanedBook;
import com.librarysystem.entity.Member;
import com.librarysystem.repository.LoanedBookRepository;

@Service("loanedBookService")
public class LoanedBookServiceImpl implements LoanedBookService{

	@Autowired
	private LoanedBookRepository loanedBookRepository;

	@Override
	public List<LoanedBook> findLoanedBookByCardId(int loanId) {
		return loanedBookRepository.findLoanedBookByMember_CardId(loanId);
	}

	@Override
	public void loanBook(LoanedBook loanedBook) {
			loanedBookRepository.save(loanedBook);
		
	}
	
	
	
}

package com.librarysystem.service;

import java.util.List;

import com.librarysystem.entity.LoanedBook;
import com.librarysystem.entity.Member;

public interface LoanedBookService {

	List<LoanedBook> findLoanedBookByCardId(int cardId);

	void loanBooks(List<String> booksToLoan, Member member);

}

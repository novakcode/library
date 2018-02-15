package com.librarysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.LoanedBook;

@Service("loanedBookRepository")
public interface LoanedBookRepository extends JpaRepository<LoanedBook,Integer>,CrudRepository<LoanedBook,Integer>{
	

		List<LoanedBook> findLoanedBookByMember_CardId(int cardId); 
		
		
		
}

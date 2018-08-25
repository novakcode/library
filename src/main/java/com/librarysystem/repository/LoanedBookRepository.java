package com.librarysystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.librarysystem.entity.LoanedBook;
import com.librarysystem.entity.Member;

@Repository("loanedBookRepository")
public interface LoanedBookRepository extends JpaRepository<LoanedBook,Integer>,CrudRepository<LoanedBook,Integer>{
	

		List<LoanedBook> findLoanedBookByMember_CardId(int cardId); 
		
	
		@Modifying
		@Query("DELETE FROM LoanedBook lb WHERE lb.member.cardId = :#{#member.cardId}")
		void deleteLoanedBookByMember_CardId(@Param("member") Member member);
		
		
		
		
		
}

package com.librarysystem.entity;

import java.time.LocalDate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class LoanedBookEntityTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void createLoanedBookWhenMemberIsNullShouldThrowException() {

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Member should not be null.");
		
		new LoanedBook(null,new Book(),LocalDate.now(),LocalDate.now());
	}

	@Test
	public void createLoanedBookWhenBookIsNullShouldThrowException() {
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Book should not be null.");
		
		
		new LoanedBook(new Member(),null,LocalDate.now(),LocalDate.now());
		
	}
	


}

package com.librarysystem.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.Assert;

@Entity
@Table(name = "Loaned_Books")
public class LoanedBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_Id", nullable = false)
	private int loanId;

	@Column(name = "date_out", nullable = false)
	private LocalDate dateOut;

	@Column(name = "date_due", nullable = false)
	private LocalDate dateDue;

	@OneToOne
	@JoinColumn(name = "isbn")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "card_Id")
	private Member member;
	
	
	public LoanedBook(){}
	
	public LoanedBook(Member member,Book book,LocalDate dateOut,LocalDate dateDue){
		
		Assert.notNull(member,"Member should not be null.");
		Assert.notNull(book,"Book should not be null.");
		this.member = member;
		this.book = book;
		this.dateOut = dateOut;
		this.dateDue = dateDue;
	}
	

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public LocalDate getDateOut() {
		return dateOut;
	}

	public void setDateOut(LocalDate dateOut) {
		this.dateOut = dateOut;
	}

	public LocalDate getDateDue() {
		return dateDue;
	}

	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}

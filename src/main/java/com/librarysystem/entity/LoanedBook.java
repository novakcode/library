package com.librarysystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Loaned_Books")
public class LoanedBook {
		
		@Id
		@GeneratedValue
		@Column(name="loan_Id",nullable = false)
		private int loanId;
		
		@Column(name="date_out",nullable  = false)
		private Date dateOut;

		@Column(name="date_due",nullable = false)
		private Date dateDue;
		
		@ManyToOne
		@JoinColumn(name="isbn")
		private Book book;
		
		@ManyToOne
		@JoinColumn(name="card_Id")
		private Member member;

		public int getLoanId() {
			return loanId;
		}

		public void setLoanId(int loanId) {
			this.loanId = loanId;
		}

		public Date getDateOut() {
			return dateOut;
		}

		public void setDateOut(Date dateOut) {
			this.dateOut = dateOut;
		}


		public Date getDateDue() {
			return dateDue;
		}

		public void setDateDue(Date dateDue) {
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

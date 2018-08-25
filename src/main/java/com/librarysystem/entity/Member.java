package com.librarysystem.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="Members")
public class Member {
		
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="card_Id",nullable = false)
		private int cardId;
		
		@Pattern(regexp="[a-zA-Z ]+",message="Name should only contain letters.")
		@Column(name="full_name",nullable = false,length = 40)
		private String fullName;
		
		@Column(name="address",length = 80,nullable = false)
		private String address;
		
		@Pattern(regexp="[0-9]+",message="Phone not valid.")
		@Column(name="phone",length  = 9,nullable = false)
		private String phone;
		
		@Column(name="date_registered",nullable = false)
		private LocalDate dateRegistered;

	
		@OneToMany(mappedBy="member")
		private List<LoanedBook> loanedBooks;
		
		public Member(){}
		
		
		

		public Member(int cardId,String fullName,
				String address,String phone) {
	
			this.cardId = cardId;
			this.fullName = fullName;
			this.address = address;
			this.phone = phone;
			this.dateRegistered = LocalDate.now();
		}




		public int getCardId() {
			return cardId;
		}

		public void setCardId(int cardId) {
			this.cardId = cardId;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public List<LoanedBook> getLoanedBooks() {
			return loanedBooks;
		}

		public void setLoanedBooks(List<LoanedBook> loanedBooks) {
			this.loanedBooks = loanedBooks;
		}

		public LocalDate getDateRegistered() {
			return dateRegistered;
		}

		public void setDateRegistered(LocalDate dateRegistered) {
			this.dateRegistered = dateRegistered;
		}




		@Override
		public String toString() {
			return "Member [cardId=" + cardId + ", fullName=" + fullName + ", address=" + address + ", phone=" + phone
					+ ", dateRegistered=" + dateRegistered  + "]";
		}
		
		
		
		
		
}

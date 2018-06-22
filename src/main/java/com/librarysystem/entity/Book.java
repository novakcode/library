package com.librarysystem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.librarysystem.validators.IsbnConstraint;

@Entity
@Table(name="Books")
public class Book {
		
		
	
	   
		@IsbnConstraint
		@Id
		@Column(name="isbn",length =  17,nullable =false)
		private String isbn;
		
		
		@Pattern(regexp="[a-zA-Z0-9 ]+",message="Title must contain only letters and numbers.")
		@Column(name="title",length = 120,nullable =false)
		private String title;
		
		
		
		@Pattern(regexp="[a-zA-Z ]+",message = "Author name must contain only letters.")
		@Column(name="author",length = 40,nullable = false)
		private String author;
		
		
		@Column(name="available",nullable = false)
		private boolean available;
		
		@Pattern(regexp="[A-Z][0-9]{2}")
		@Column(name="section",nullable = false)
		private String section;
		
		
		@OneToMany(mappedBy="book" ,fetch = FetchType.LAZY,cascade=CascadeType.ALL)
		private List<LoanedBook> loanedBooks;
		
		
		
		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}
		
		

		public String getSection() {
			return section;
		}

		public void setSection(String section) {
			this.section = section;
		}

		@Override
		public String toString() {
			return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", available=" + available
					+ ", section=" + section + ", loanedBooks=" + loanedBooks + "]";
		}

		
	
	
		
		
	
	
}

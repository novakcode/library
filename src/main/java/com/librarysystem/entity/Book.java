package com.librarysystem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Books")
public class Book {
		
		
		@Id
		@Column(name="isbn",length =  17,nullable =false)
		private String isbn;
		
		@Column(name="title",length = 120,nullable =false)
		private String title;
		
		@Column(name="author",length = 40,nullable = false)
		private String author;
		
		@Column(name="available",nullable = false)
		private boolean available;
		
		@OneToMany(mappedBy="book")
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
	
	
		
	
	
}

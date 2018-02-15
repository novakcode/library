package com.librarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.librarysystem.entity.Book;

public interface BookService {
		
		Book findBookByIsbn(String isbn);
		Page<Book> findBookByTitle(String title,Pageable pageable);
		Page<Book> findBookByAuthor(String author,Pageable pageable);
		void createOrUpdateBook(Book book);
}

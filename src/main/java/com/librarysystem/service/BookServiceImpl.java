package com.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.Book;
import com.librarysystem.repository.BookRepository;

@Service("bookService")
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book findBookByIsbn(String isbn) {
		return bookRepository.findBookByIsbn(isbn);
	}

	
	
	@Override
	public Page<Book> findBookByTitle(String title, Pageable pageable) {
		return bookRepository.findBookByTitleContaining(title, pageable);
	}



	@Override
	public Page<Book> findBookByAuthor(String author, Pageable pageable) {
		return bookRepository.findBookByAuthorContaining(author, pageable);
	}



	@Override
	public void createOrUpdateBook(Book book) {
			bookRepository.save(book);
		
	}

}

package com.librarysystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.Book;
import com.librarysystem.repository.BookRepository;

@Service("bookService")
public class BookServiceImpl implements BookService{

	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book findBookByIsbn(String isbn) {
		logger.debug("Finding book by isbn:{}",isbn);
		return bookRepository.findBookByIsbn(isbn);
	}

	
	
	@Override
	public Page<Book> findBookByTitle(String title, Pageable pageable) {
		logger.debug("Finding books by title:{}",title);
		logger.debug("Page:{}",pageable.getPageNumber());
		return bookRepository.findBookByTitleContaining(title, pageable);
	}



	@Override
	public Page<Book> findBookByAuthor(String author, Pageable pageable) {
		logger.debug("Finding books by author:{}",author);
		logger.debug("Page:{}",pageable.getPageNumber());
		return bookRepository.findBookByAuthorContaining(author, pageable);
	}



	@Override
	public Book createOrUpdateBook(Book book) {
		logger.debug("Creating book:{}",book);
		return	bookRepository.save(book);
	
	}

}

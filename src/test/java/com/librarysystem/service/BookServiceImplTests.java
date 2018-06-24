package com.librarysystem.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.librarysystem.entity.Book;
import com.librarysystem.repository.BookRepository;

@RunWith(SpringRunner.class)
public class BookServiceImplTests {

	private static final int PAGE_SIZE = 5;
	private static final int PAGE = 1;
	private static final String ISBN = "235-4-247821-45-1";
	private static final String TITLE = "Title";
	private static final String AUTHOR = "Author";
	private static final String SECTION = "A01";

	@TestConfiguration
	static class BookServiceImplTestContextConfiguration {

		@Bean
		public BookService bookService() {
			return new BookServiceImpl();
		}
	}

	@Autowired
	private BookService bookService;

	@MockBean
	public BookRepository bookRepository;

	@Before
	public void setUp() {
		Book mockBook = new Book(ISBN, TITLE, AUTHOR, true, SECTION);
		Page<Book> books = Page.empty(PageRequest.of(PAGE, PAGE_SIZE));

		when(bookRepository.findBookByIsbn(ISBN)).thenReturn(mockBook);
		when(bookRepository.save(any(Book.class))).thenReturn(mockBook);
		when(bookRepository.findBookByTitleContaining(any(String.class), any(Pageable.class))).thenReturn(books);
		when(bookRepository.findBookByAuthorContaining(any(String.class), any(Pageable.class))).thenReturn(books);

	}

	@Test
	public void whenFindByExistingIsbn_thenReturnBook() {
		Book foundBook = bookService.findBookByIsbn(ISBN);

		verify(bookRepository, times(1)).findBookByIsbn(ISBN);
		verifyNoMoreInteractions(bookRepository);

		assertThat(foundBook.getIsbn(), equalTo(ISBN));
	}

	@Test
	public void createNewBookShouldReturnCreatedBook() {
		Book book = new Book(ISBN, TITLE, AUTHOR, true, SECTION);

		Book foundBook = bookService.createOrUpdateBook(book);

		verify(bookRepository, times(1)).save(book);
		verifyNoMoreInteractions(bookRepository);

		assertThat(foundBook.getIsbn(), equalTo(ISBN));

	}

	@Test
	public void whenFindByTitleContainingReturnList() {

		List<Book> books = new ArrayList<Book>();

		Page<Book> bookByPage = bookService.findBookByTitle("", PageRequest.of(1, 5));

		verify(bookRepository, times(1)).findBookByTitleContaining("", PageRequest.of(1, 5));
		verifyNoMoreInteractions(bookRepository);

		assertEquals(books, bookByPage.getContent());

	}

	@Test
	public void whenFindByAuthorContaningReturnList() {

		List<Book> books = new ArrayList<Book>();

		Page<Book> bookByPage = bookService.findBookByAuthor("", PageRequest.of(1, 5));

		verify(bookRepository, times(1)).findBookByAuthorContaining("", PageRequest.of(1, 5));
		verifyNoMoreInteractions(bookRepository);

		assertEquals(books, bookByPage.getContent());
	}
}

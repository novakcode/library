package com.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.librarysystem.entity.Book;

@Repository("bookRepository")
public interface BookRepository extends JpaRepository<Book,String>,CrudRepository<Book,String>{
		
		Book findBookByIsbn(String isbn);
		Page<Book> findBookByTitleContaining(String title,Pageable pageable);
		Page<Book> findBookByAuthor(String author,Pageable pageable);
	
}

package com.bookcatalog.repository;

import com.bookcatalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // case-insensitive partial match search
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}

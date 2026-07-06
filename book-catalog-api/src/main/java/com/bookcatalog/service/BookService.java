package com.bookcatalog.service;

import com.bookcatalog.dto.BookRequest;
import com.bookcatalog.dto.BookResponse;
import com.bookcatalog.exception.ResourceNotFoundException;
import com.bookcatalog.model.Book;
import com.bookcatalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookResponse createBook(BookRequest request) {
        Book book = new Book();
        mapRequestToEntity(request, book);
        Book saved = bookRepository.save(book);
        return toResponse(saved);
    }

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = findBookOrThrow(id);
        return toResponse(book);
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = findBookOrThrow(id);
        mapRequestToEntity(request, book);
        Book updated = bookRepository.save(book);
        return toResponse(updated);
    }

    public void deleteBook(Long id) {
        Book book = findBookOrThrow(id);
        bookRepository.delete(book);
    }

    // search by title OR author (partial, case-insensitive)
    public List<BookResponse> searchBooks(String title, String author) {
        List<Book> results;

        if (title != null && author != null) {
            results = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(title, author);
        } else if (title != null) {
            results = bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (author != null) {
            results = bookRepository.findByAuthorContainingIgnoreCase(author);
        } else {
            results = bookRepository.findAll();
        }

        return results.stream().map(this::toResponse).collect(Collectors.toList());
    }

    private Book findBookOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    private void mapRequestToEntity(BookRequest request, Book book) {
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setGenre(request.getGenre());
        book.setPublicationYear(request.getPublicationYear());
        book.setAvailability(request.isAvailability());
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublicationYear(),
                book.isAvailability()
        );
    }
}

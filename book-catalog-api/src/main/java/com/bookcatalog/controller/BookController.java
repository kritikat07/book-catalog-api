package com.bookcatalog.controller;

import com.bookcatalog.dto.BookRequest;
import com.bookcatalog.dto.BookResponse;
import com.bookcatalog.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // POST /api/books -> add a new book
    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.createBook(request));
    }

    // GET /api/books -> list all books
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET /api/books/{id} -> get single book
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    // PUT /api/books/{id} -> update a book
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id,
                                                    @Valid @RequestBody BookRequest request) {
        return ResponseEntity.ok(bookService.updateBook(id, request));
    }

    // DELETE /api/books/{id} -> delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/books/search?title=xxx&author=yyy -> search by title or author
    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return ResponseEntity.ok(bookService.searchBooks(title, author));
    }
}

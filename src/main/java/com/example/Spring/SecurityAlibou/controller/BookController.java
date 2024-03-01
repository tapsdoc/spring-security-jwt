package com.example.Spring.SecurityAlibou.controller;

import com.example.Spring.SecurityAlibou.domain.Book;
import com.example.Spring.SecurityAlibou.repository.BookRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'management:read')")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> saveBook(@Valid @RequestBody BookRequest request) {
        Book book = Book.builder()
            .title(request.title())
            .author(request.author())
            .published(LocalDate.now())
            .build();
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookRequest request) {

        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalStateException("Book not found"));
        if (request.title() != null) {
            book.setTitle(request.title());
        }
        if (request.author() != null) {
            book.setAuthor(request.author());
        }
        return ResponseEntity.ok(bookRepository.save(book));
    }
}

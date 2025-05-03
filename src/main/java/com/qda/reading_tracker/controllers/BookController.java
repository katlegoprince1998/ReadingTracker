package com.qda.reading_tracker.controllers;

import com.qda.reading_tracker.dtos.request.BookRequestDto;
import com.qda.reading_tracker.dtos.response.BookResponseDto;
import com.qda.reading_tracker.services.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book/api/v1")
public class BookController {

    private final BookService service;

    @PostMapping("/create")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto request) throws Exception {
        BookResponseDto response = service.createBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long bookId,
            @RequestBody BookRequestDto request
    ) {
        BookResponseDto updated = service.updateBook(request, bookId);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long bookId) {
        BookResponseDto response = service.getBookById(bookId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        String message = service.deleteBook(bookId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        List<BookResponseDto> books = service.getAllBooks();
        return ResponseEntity.ok(books);
    }
}


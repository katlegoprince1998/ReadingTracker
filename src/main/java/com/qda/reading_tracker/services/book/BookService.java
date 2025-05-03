package com.qda.reading_tracker.services.book;

import com.qda.reading_tracker.dtos.request.BookRequestDto;
import com.qda.reading_tracker.dtos.response.BookResponseDto;
import com.qda.reading_tracker.exception.BookNotFoundException;

import java.util.List;

public interface BookService {
   BookResponseDto createBook(BookRequestDto request) throws Exception;
   BookResponseDto updateBook(BookRequestDto requestDto, Long bookId) throws BookNotFoundException;
   BookResponseDto getBookById(Long bookId) throws BookNotFoundException;
   String deleteBook(Long bookId) throws BookNotFoundException;
   List<BookResponseDto> getAllBooks();
}

package com.qda.reading_tracker.services.book;

import com.qda.reading_tracker.dtos.request.BookRequestDto;
import com.qda.reading_tracker.dtos.response.BookResponseDto;
import com.qda.reading_tracker.exception.BookNotFoundException;
import com.qda.reading_tracker.mappers.book.BookMapper;
import com.qda.reading_tracker.models.Book;
import com.qda.reading_tracker.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    private final BookRepository repository;


    @Override
    public BookResponseDto createBook(BookRequestDto request) {
        if (request == null) {
            throw new IllegalArgumentException("Request object cannot be null");
        }

        Book book = BookMapper.toEntity(request);
        Book savedBook = repository.save(book);

        return BookMapper.toDto(savedBook);
    }

    @Override
    public BookResponseDto updateBook(BookRequestDto requestDto, Long bookId) throws BookNotFoundException {
        if (requestDto == null){
            throw new IllegalArgumentException("request object cannot be null");
        }

        Book book = repository.findById(bookId).orElseThrow(
                () -> new BookNotFoundException("Book with id " + bookId + " was not found"));

        Book updatedBook = updateBook(requestDto, book);

        return BookMapper.toDto(updatedBook);

    }

    @Override
    public BookResponseDto getBookById(Long bookId) throws BookNotFoundException {
       Book book = repository.findById(bookId).orElseThrow(
               () -> new BookNotFoundException("Book with Id " + bookId + " was not found"));

       return BookMapper.toDto(book);

    }

    @Override
    public String deleteBook(Long bookId) throws BookNotFoundException {
        Book book = repository.findById(bookId).orElseThrow(
                () -> new BookNotFoundException("Book with Id " + bookId + " was not found"));

        repository.delete(book);

        return "Book with ID " + bookId + " was deleted";
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
       List<Book> books = repository.findAll();

       if (books.isEmpty())
           throw new BookNotFoundException("Books were not found");

       return books
               .stream()
               .map(BookMapper::toDto)
               .collect(Collectors.toList());
    }

    private static Book updateBook(BookRequestDto dto, Book book) {
        if (dto == null || book == null) {
            throw new IllegalArgumentException("DTO and Book must not be null");
        }

        if (dto.title() != null) {
            book.setTitle(dto.title());
        }
        if (dto.author() != null) {
            book.setAuthor(dto.author());
        }
        if (dto.totalNumberOfPages() > 0) {
            book.setTotalNumberOfPages(dto.totalNumberOfPages());
        }
        if (dto.coverPageImage() != null) {
            book.setCoverPageImage(dto.coverPageImage());
        }
        if (dto.status() != null) {
            book.setStatus(dto.status());
        }
        if (dto.progressInPercentage() >= 0 && dto.progressInPercentage() <= 100) {
            book.setProgressInPercentage(dto.progressInPercentage());
        }
        if (dto.userId() != null) {
            book.setUserId(dto.userId());
        }

        book.setUpdatedAt(LocalDateTime.now());
        return book;
    }

}

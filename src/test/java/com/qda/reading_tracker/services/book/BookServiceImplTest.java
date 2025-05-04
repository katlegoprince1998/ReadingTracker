package com.qda.reading_tracker.services.book;

import com.qda.reading_tracker.dtos.request.BookRequestDto;
import com.qda.reading_tracker.dtos.response.BookResponseDto;
import com.qda.reading_tracker.mappers.book.BookMapper;
import com.qda.reading_tracker.models.Book;
import com.qda.reading_tracker.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

  @Mock
  private BookRepository repository;

  @InjectMocks
  private BookServiceImpl service;

  @Test
  void createBook_whenValidRequest_isPassed_then_should_return_bookResponse() {
    // Arrange
    LocalDateTime fixedNow = LocalDateTime.of(2023, 1, 1, 12, 0);

    BookRequestDto request = new BookRequestDto("Unit tests", "Katlego Mashego", 100,
            "My cover page", "In progress", 20, "userid");

    Book book = Book.builder()
            .userId("userid")
            .createdAt(fixedNow)
            .updatedAt(fixedNow)
            .status("In progress")
            .totalNumberOfPages(100)
            .title("Unit tests")
            .author("Katlego Mashego")
            .coverPageImage("My cover page")
            .progressInPercentage(20)
            .build();

    Book savedBook = Book.builder()
            .bookId(10L)
            .userId("userid")
            .createdAt(fixedNow)
            .updatedAt(fixedNow)
            .status("In progress")
            .totalNumberOfPages(100)
            .title("Unit tests")
            .author("Katlego Mashego")
            .coverPageImage("My cover page")
            .progressInPercentage(20)
            .build();

    BookResponseDto expectedResponse = new BookResponseDto(
            10L, "Unit tests", "Katlego Mashego", 100,
            "My cover page", "In progress", fixedNow, fixedNow, 10,
            "userid");

    try (MockedStatic<BookMapper> mapper = Mockito.mockStatic(BookMapper.class)) {
      mapper.when(() -> BookMapper.toEntity(request)).thenReturn(book);
      mapper.when(() -> BookMapper.toDto(savedBook)).thenReturn(expectedResponse);
      when(repository.save(book)).thenReturn(savedBook);

      // Act
      BookResponseDto result = service.createBook(request);

      // Assert
      Assertions.assertEquals(expectedResponse, result);
      verify(repository, times(1)).save(book);
    }
  }
}

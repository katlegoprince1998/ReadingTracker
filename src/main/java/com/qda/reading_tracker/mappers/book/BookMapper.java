package com.qda.reading_tracker.mappers.book;

import com.qda.reading_tracker.dtos.request.BookRequestDto;
import com.qda.reading_tracker.dtos.response.BookResponseDto;
import com.qda.reading_tracker.models.Book;

import java.time.LocalDateTime;

public class BookMapper {

    public static Book toEntity(BookRequestDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("BookRequestDto cannot be null");
        }

        return Book.builder()
                .title(dto.title())
                .author(dto.author())
                .coverPageImage(dto.coverPageImage())
                .progressInPercentage(dto.progressInPercentage())
                .totalNumberOfPages(dto.totalNumberOfPages())
                .userId(dto.userId())
                .status(dto.status())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static BookResponseDto toDto(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book entity cannot be null");
        }

        return new BookResponseDto(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getTotalNumberOfPages(),
                book.getCoverPageImage(),
                book.getStatus(),
                book.getCreatedAt(),
                book.getUpdatedAt(),
                book.getProgressInPercentage(),
                book.getUserId()
        );
    }
}

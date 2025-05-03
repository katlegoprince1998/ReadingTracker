package com.qda.reading_tracker.mappers.dailynote;

import com.qda.reading_tracker.dtos.request.DailyNoteRequestDto;
import com.qda.reading_tracker.dtos.response.DailyNoteResponseDto;
import com.qda.reading_tracker.mappers.book.BookMapper;
import com.qda.reading_tracker.models.Book;
import com.qda.reading_tracker.models.DailyNote;

import java.time.LocalDateTime;

public class DailyNoteMapper {

    public static DailyNote toEntity(DailyNoteRequestDto dto, Book book) {
        if (dto == null) {
            throw new IllegalArgumentException("DailyNoteRequestDto cannot be null");
        }

        return DailyNote.builder()
                .heading(dto.heading())
                .content(dto.content())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .userId(dto.userId())
                .book(book)
                .build();
    }

    public static DailyNoteResponseDto toDto(DailyNote note) {
        if (note == null) {
            throw new IllegalArgumentException("DailyNote entity cannot be null");
        }

        return new DailyNoteResponseDto(
                note.getDailyNoteId(),
                note.getHeading(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt(),
                note.getUserId(),
                BookMapper.toDto(note.getBook())
        );
    }
}

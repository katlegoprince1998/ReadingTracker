package com.qda.reading_tracker.dtos.response;

import java.time.LocalDateTime;

public record DailyNoteResponseDto(
        Long dailyNoteId,
        String heading,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String userId,
        BookResponseDto book
) {}

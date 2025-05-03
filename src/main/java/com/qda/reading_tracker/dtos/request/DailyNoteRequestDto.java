package com.qda.reading_tracker.dtos.request;

public record DailyNoteRequestDto(
        String heading,
        String content,
        String userId,
        Long bookId
) {}
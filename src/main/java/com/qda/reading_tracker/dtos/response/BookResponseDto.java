package com.qda.reading_tracker.dtos.response;

import java.time.LocalDateTime;

public record BookResponseDto(
        Long bookId,
        String title,
        String author,
        int totalNumberOfPages,
        String coverPageImage, // Base64 as String
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        int progressInPercentage,
        String userId
) {}

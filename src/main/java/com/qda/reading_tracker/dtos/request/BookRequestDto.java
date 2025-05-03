package com.qda.reading_tracker.dtos.request;

public record BookRequestDto(
        String title,
        String author,
        int totalNumberOfPages,
        String coverPageImage,
        String status,
        int progressInPercentage,
        String userId
) {}
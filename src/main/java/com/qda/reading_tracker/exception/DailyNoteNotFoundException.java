package com.qda.reading_tracker.exception;

public class DailyNoteNotFoundException extends RuntimeException {
    public DailyNoteNotFoundException(String message) {
        super(message);
    }
}

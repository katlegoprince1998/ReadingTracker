package com.qda.reading_tracker.services.dailynote;

import com.qda.reading_tracker.dtos.request.DailyNoteRequestDto;
import com.qda.reading_tracker.dtos.response.DailyNoteResponseDto;
import com.qda.reading_tracker.exception.DailyNoteNotFoundException;

import java.util.List;

public interface DailyNoteService {
    DailyNoteResponseDto createDailyNote(DailyNoteRequestDto request);
    DailyNoteResponseDto updateDailyNote(DailyNoteRequestDto request, Long dailyNoteId) throws DailyNoteNotFoundException;
    String deleteDailyNote(Long dailyNoteId) throws DailyNoteNotFoundException;
    DailyNoteResponseDto getDailyNoteById(Long dailyNoteById) throws DailyNoteNotFoundException;
    List<DailyNoteResponseDto> getAllDailyNotes();
}

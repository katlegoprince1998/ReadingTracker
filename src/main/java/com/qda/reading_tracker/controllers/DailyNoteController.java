package com.qda.reading_tracker.controllers;

import com.qda.reading_tracker.dtos.request.DailyNoteRequestDto;
import com.qda.reading_tracker.dtos.response.DailyNoteResponseDto;
import com.qda.reading_tracker.services.dailynote.DailyNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/daily-note/api/v1")
@RequiredArgsConstructor
public class DailyNoteController {

    private final DailyNoteService dailyNoteService;

    @PostMapping("/create")
    public ResponseEntity<DailyNoteResponseDto> create(@RequestBody DailyNoteRequestDto request) {
        DailyNoteResponseDto response = dailyNoteService.createDailyNote(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DailyNoteResponseDto> update(@RequestBody DailyNoteRequestDto request,
                                                       @PathVariable("id") Long dailyNoteId) {
        DailyNoteResponseDto response = dailyNoteService.updateDailyNote(request, dailyNoteId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long dailyNoteId) {
        String message = dailyNoteService.deleteDailyNote(dailyNoteId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DailyNoteResponseDto> getById(@PathVariable("id") Long dailyNoteId) {
        DailyNoteResponseDto response = dailyNoteService.getDailyNoteById(dailyNoteId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DailyNoteResponseDto>> getAll() {
        List<DailyNoteResponseDto> allNotes = dailyNoteService.getAllDailyNotes();
        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }
}

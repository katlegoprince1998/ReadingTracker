package com.qda.reading_tracker.services.dailynote;

import com.qda.reading_tracker.dtos.request.DailyNoteRequestDto;
import com.qda.reading_tracker.dtos.response.DailyNoteResponseDto;
import com.qda.reading_tracker.exception.DailyNoteNotFoundException;
import com.qda.reading_tracker.mappers.dailynote.DailyNoteMapper;
import com.qda.reading_tracker.models.Book;
import com.qda.reading_tracker.models.DailyNote;
import com.qda.reading_tracker.repository.BookRepository;
import com.qda.reading_tracker.repository.DailyNoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyNoteServiceImpl implements DailyNoteService {

    private final DailyNoteRepository repository;
    private final BookRepository bookRepository;

    @Override
    public DailyNoteResponseDto createDailyNote(DailyNoteRequestDto request) {
        if (request == null) {
            throw new IllegalArgumentException("Request object cannot be null");
        }

        Book book = bookRepository.findById(request.bookId())
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + request.bookId() + " not found"));

        DailyNote dailyNote = DailyNoteMapper.toEntity(request, book);
        dailyNote.setBook(book);
        dailyNote.setCreatedAt(LocalDateTime.now());
        dailyNote.setUpdatedAt(LocalDateTime.now());

        DailyNote saved = repository.save(dailyNote);
        return DailyNoteMapper.toDto(saved);
    }

    @Override
    public DailyNoteResponseDto updateDailyNote(DailyNoteRequestDto request, Long dailyNoteId) throws DailyNoteNotFoundException {
        DailyNote note = repository.findById(dailyNoteId)
                .orElseThrow(() -> new DailyNoteNotFoundException("Daily note with id " + dailyNoteId + " not found"));

        if (request.heading() != null) note.setHeading(request.heading());
        if (request.content() != null) note.setContent(request.content());
        if (request.userId() != null) note.setUserId(request.userId());
        if (request.bookId() != null) {
            Book book = bookRepository.findById(request.bookId())
                    .orElseThrow(() -> new IllegalArgumentException("Book with id " + request.bookId() + " not found"));
            note.setBook(book);
        }

        note.setUpdatedAt(LocalDateTime.now());

        DailyNote updated = repository.save(note);
        return DailyNoteMapper.toDto(updated);
    }

    @Override
    public String deleteDailyNote(Long dailyNoteId) throws DailyNoteNotFoundException {
        DailyNote note = repository.findById(dailyNoteId)
                .orElseThrow(() -> new DailyNoteNotFoundException("Daily note with id " + dailyNoteId + " not found"));

        repository.delete(note);
        return "Daily note with ID " + dailyNoteId + " was deleted";
    }

    @Override
    public DailyNoteResponseDto getDailyNoteById(Long dailyNoteId) throws DailyNoteNotFoundException {
        DailyNote note = repository.findById(dailyNoteId)
                .orElseThrow(() -> new DailyNoteNotFoundException("Daily note with id " + dailyNoteId + " not found"));

        return DailyNoteMapper.toDto(note);
    }

    @Override
    public List<DailyNoteResponseDto> getAllDailyNotes() {
        List<DailyNote> notes = repository.findAll();

        if (notes.isEmpty()) {
            throw new DailyNoteNotFoundException("No daily notes found");
        }

        return notes.stream()
                .map(DailyNoteMapper::toDto)
                .collect(Collectors.toList());
    }
}


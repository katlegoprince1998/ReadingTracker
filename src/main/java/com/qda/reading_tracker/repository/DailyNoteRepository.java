package com.qda.reading_tracker.repository;

import com.qda.reading_tracker.models.DailyNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyNoteRepository extends JpaRepository<DailyNote, Long> {
}

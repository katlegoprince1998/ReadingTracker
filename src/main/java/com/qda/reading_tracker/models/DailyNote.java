package com.qda.reading_tracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "dailynotetbl")
@AllArgsConstructor
@NoArgsConstructor
public class DailyNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dailyNoteId;
    private String heading;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}

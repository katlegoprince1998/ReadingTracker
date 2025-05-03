package com.qda.reading_tracker.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@Table(name = "booktbl")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String title;
    private String author;
    private int totalNumberOfPages;
    private String coverPageImage;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int progressInPercentage;
    private String userId;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyNote> notes = new ArrayList<>();
}

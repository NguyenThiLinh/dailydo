package com.example.dailydo.model;

import javax.persistence.*;
import com.example.dailydo.model.enums.Priority;
import com.example.dailydo.model.enums.Status;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "priority", nullable = false)
    private Priority priority = Priority.MEDIUM;

    @Column(name="status", nullable = false)
    private Status status = Status.PENDING;

    private LocalDateTime deadline ;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;

    @Column(name="updated_at")
    private LocalDateTime updated_at;

    @PrePersist
    protected  void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = created_at;
    }

    @PreUpdate
    protected  void onUpdate() {
        updated_at = LocalDateTime.now();
    }
}


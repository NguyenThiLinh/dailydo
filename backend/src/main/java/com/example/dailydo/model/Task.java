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
public class Task extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "priority", nullable = false)
    @Builder.Default
    private Priority priority = Priority.MEDIUM;

    @Column(name="status", nullable = false)
    @Builder.Default
    private Status status = Status.PENDING;

    private LocalDateTime deadline ;

}


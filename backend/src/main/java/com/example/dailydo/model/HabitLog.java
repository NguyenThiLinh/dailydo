package com.example.dailydo.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "habit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "log_date", nullable = false)
    private LocalDate logDate;

    @PrePersist
    public void prePersist() {
        super.prePersist();
        if (logDate == null) {
            logDate = LocalDate.now();
        }
    }
}
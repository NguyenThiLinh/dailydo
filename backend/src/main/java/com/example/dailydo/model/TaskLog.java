package com.example.dailydo.model;

import com.example.dailydo.model.enums.ActionType;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "task_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "action_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ActionType actionType = ActionType.CREATE;

    @Column(columnDefinition = "TEXT")
    private String oldValue;

    @Column(columnDefinition = "TEXT")
    private String newValue;

    @Column(length = 255)
    private String note;

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

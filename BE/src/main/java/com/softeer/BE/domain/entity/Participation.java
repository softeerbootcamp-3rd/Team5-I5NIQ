package com.softeer.BE.domain.entity;

import com.softeer.BE.domain.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Table(name = "participation")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "schedule")
    private Schedule schedule;

    private boolean completion;

    private Long participants;

    private LocalDateTime paidAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;
}

package com.softeer.BE.domain.entity;

import com.softeer.BE.domain.entity.enums.Status;
import com.softeer.BE.repository.ParticipationRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "participation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Participation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "class_car_id")
    private ClassCar classCar;

    private boolean completion;

    private Long participants;

    private LocalDateTime paidAt;

    @Enumerated(EnumType.STRING)
    private Status status;
    public static long makeReservation(ClassCar classCar, Users user, long amount, ParticipationRepository repository){
        Participation participation =
                new Participation(null,user,classCar,false,amount,null,Status.UNPAID);
        Participation createParticipation = repository.save(participation);
        return createParticipation.getId();
    }
    public boolean hasPaid(){
        return status.equals(Status.PAID);
    }
}

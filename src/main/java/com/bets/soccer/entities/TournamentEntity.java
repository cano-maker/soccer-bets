package com.bets.soccer.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "Tournament")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private String logoPath;
}

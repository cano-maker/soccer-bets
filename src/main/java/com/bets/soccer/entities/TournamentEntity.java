package com.bets.soccer.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "Tournament")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private String logoPath;

    @OneToMany(mappedBy="tournament")
    private Set<CategoryEntity> categories;
}

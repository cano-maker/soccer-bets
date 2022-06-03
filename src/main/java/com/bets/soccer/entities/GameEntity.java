package com.bets.soccer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Game")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private String teamVisitorId;
    private String teamLocalId;
    private Integer goalsVisitor;
    private Integer goalsLocal;
    private boolean isClosed;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private TeamEntity team;
}

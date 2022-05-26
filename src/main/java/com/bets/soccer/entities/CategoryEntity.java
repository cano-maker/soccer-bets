package com.bets.soccer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Category")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="tournament_id", nullable=false)
    private TournamentEntity tournament;

}

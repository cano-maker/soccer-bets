package com.bets.soccer.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "CategoryDetail")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetailEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Integer gamesPlayed;
    private Integer gamesWon;
    private Integer gamesTied;
    private Integer gamesLost;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name="team_id", nullable=false)
    private TeamEntity team;


}

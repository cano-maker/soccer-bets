package com.bets.soccer.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class CategoryDetail
{
    private final Long id;
    private final Integer gamesPlayed;
    private final Integer gamesWon;
    private final Integer gamesTied;
    private final Integer gamesLost;
    private final Integer points;
    private final Integer goalsFor;
    private final Integer goalsAgainst;
    private final Integer goalDifference;

}

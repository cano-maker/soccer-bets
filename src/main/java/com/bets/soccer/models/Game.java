package com.bets.soccer.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
public class Game {

    private final Long id;
    private final LocalDate date;
    private final String teamVisitorId;
    private final String teamLocalId;
    private final Integer goalsVisitor;
    private final Integer goalsLocal;
    private final boolean isClosed;

}

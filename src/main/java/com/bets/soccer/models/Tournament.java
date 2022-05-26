package com.bets.soccer.models;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@Builder
@Getter
public class Tournament
{
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final boolean isActive;
    private final String logoPath;
}

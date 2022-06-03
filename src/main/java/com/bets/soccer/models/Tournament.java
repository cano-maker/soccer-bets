package com.bets.soccer.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode
public class Tournament
{
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final boolean isActive;
    private final String logoPath;
    private final Set<Category> categories;

}

package com.bets.soccer.models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode
public class Team
{
    private final Long id;
    private final String name;
    private final String logoPath;
    private final Set<CategoryDetail> categoriesDetails;
    private final Set<Game> games;
}

package com.bets.soccer.models;

import com.bets.soccer.entities.CategoryDetailEntity;
import com.bets.soccer.entities.CategoryEntity;
import com.bets.soccer.entities.GameEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.Set;

@Builder
@Getter
@EqualsAndHashCode
public class Category
{

    private final Long id;
    private final String name;
    private final Set<Game> games;
    private final Set<CategoryDetail> categoriesDetails;

}

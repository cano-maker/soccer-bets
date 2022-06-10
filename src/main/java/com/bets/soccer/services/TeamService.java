package com.bets.soccer.services;

import com.bets.soccer.entities.CategoryDetailEntity;
import com.bets.soccer.entities.GameEntity;
import com.bets.soccer.entities.TeamEntity;
import com.bets.soccer.exception.RecordAlreadyExistsException;
import com.bets.soccer.exception.RecordNotValidException;
import com.bets.soccer.interfaces.TeamRepository;
import com.bets.soccer.models.CategoryDetail;
import com.bets.soccer.models.Game;
import com.bets.soccer.models.Team;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService
{
    private final TeamRepository teamRepository;

    public Optional<Team> add(Team model)
    {
        return Optional.ofNullable(model)
                .map(Team::getName)
                .map(name -> teamRepository.findTeamByName(name)
                        .map(teamEntity -> {throw new RecordAlreadyExistsException(String.format("Team %s is already present", teamEntity.getName()));})
                        .orElseGet(() -> teamRepository.save(modelToEntity(model))))
                .map(o -> Optional.of(model))
                .orElseThrow(() -> new RecordNotValidException("Team value is not valid"));
    }

    private TeamEntity modelToEntity(Team model)
    {
        return TeamEntity.builder()
                .name(model.getName())
                .logoPath(model.getLogoPath())
                .build();
    }

    public List<Team> findAll()
    {
        return teamRepository.findAll()
                .stream().map(this::entityToModel)
                .collect(Collectors.toList());
    }

    private Team entityToModel(TeamEntity entity)
    {
        return Team.builder()
                .id(entity.getId())
                .name(entity.getName())
                .logoPath(entity.getLogoPath())
                .categoriesDetails(categoriesEntitiesToModel(entity.getCategoriesDetails()))
                .games(gamesEntitiesToModel(entity.getGames()))
                .build();
    }

    private Set<CategoryDetail> categoriesEntitiesToModel(Set<CategoryDetailEntity> categoriesDetails)
    {
        return categoriesDetails.stream()
                .map(categoryDetailEntity -> categoryEntityToModel(categoryDetailEntity))
                .collect(Collectors.toSet());
    }

    private CategoryDetail categoryEntityToModel(CategoryDetailEntity entity)
    {
        return CategoryDetail.builder()
                .id(entity.getId())
                .gamesLost(entity.getGamesLost())
                .gamesPlayed(entity.getGamesPlayed())
                .gamesTied(entity.getGamesTied())
                .gamesWon(entity.getGamesWon())
                .goalDifference(entity.getGoalDifference())
                .goalsAgainst(entity.getGoalsAgainst())
                .goalsFor(entity.getGoalsFor())
                .points(entity.getPoints())
                .build();
    }

    private Set<Game> gamesEntitiesToModel(Set<GameEntity> games)
    {
        return games.stream()
                .map(gameEntity -> gameEntityToModel(gameEntity))
                .collect(Collectors.toSet());
    }

    private Game gameEntityToModel(GameEntity entity)
    {
        return Game.builder()
                .date(entity.getDate())
                .goalsLocal(entity.getGoalsLocal())
                .goalsVisitor(entity.getGoalsVisitor())
                .id(entity.getId())
                .teamLocalId(entity.getTeamLocalId())
                .teamVisitorId(entity.getTeamVisitorId())
                .isClosed(entity.isClosed())
                .build();
    }

}

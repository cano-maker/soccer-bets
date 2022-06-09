package com.bets.soccer.services;

import com.bets.soccer.entities.CategoryDetailEntity;
import com.bets.soccer.entities.CategoryEntity;
import com.bets.soccer.entities.GameEntity;
import com.bets.soccer.entities.TournamentEntity;
import com.bets.soccer.exception.RecordAlreadyExistsException;
import com.bets.soccer.exception.RecordNotFoundException;
import com.bets.soccer.interfaces.CategoryRepository;
import com.bets.soccer.interfaces.TournamentRepository;
import com.bets.soccer.models.Category;
import com.bets.soccer.models.CategoryDetail;
import com.bets.soccer.models.Game;
import com.bets.soccer.models.Tournament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TournamentService
{
    private final TournamentRepository tournamentRepository;
    private final CategoryRepository categoryRepository;

    public Tournament add(Tournament model) throws RecordAlreadyExistsException
    {
        return Optional.ofNullable(model)
                .map(Tournament::getName)
                .map(name -> tournamentRepository.findTournamentByName(name)
                        .map(tournament -> {throw new RecordAlreadyExistsException(String.format("Tournament %s is already present", model.getName()));} )
                        .orElseGet(() -> tournamentRepository.save(modelToEntity(model))))
                .map(o -> model)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Tournament doesn't exist")));
    }

    private TournamentEntity modelToEntity(Tournament model){
        return TournamentEntity.builder()
                .name(model.getName())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .logoPath(model.getLogoPath())
                .isActive(model.isActive())
                .build();
    }

    public List<Tournament> findAll()
    {
        return tournamentRepository.findAll()
                .stream().map(this::entityToModel)
                .collect(Collectors.toList());
    }

    private Tournament entityToModel(TournamentEntity entity)
    {
        return Tournament.builder()
                .name(entity.getName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .logoPath(entity.getLogoPath())
                .isActive(entity.isActive())
                .categories(categorySetEntityToModel(entity.getCategories()))
                .build();
    }

    private Set<Category> categorySetEntityToModel(Set<CategoryEntity> categories)
    {
        return categories.stream()
                .map(this::categoryEntityToModel)
                .collect(Collectors.toSet());
    }

    private Category categoryEntityToModel(CategoryEntity categoryEntity)
    {
        return Category.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .categoriesDetails(categoriesEntitiesToModel(categoryEntity.getCategoriesDetails()))
                .games(gamesEntitiesToModel(categoryEntity.getGames()))
                .build();
    }

    private Set<CategoryDetail> categoriesEntitiesToModel(Set<CategoryDetailEntity> categoriesDetails)
    {
        return categoriesDetails.stream()
                .map(this::categoryEntityToModel)
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
                .map(this::gameEntityToModel)
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

    public Category addCategory(Category model)
    {
      return Optional.ofNullable(model)
                .map(Category::getTournamentName)
                .flatMap(tournamentRepository::findTournamentByName)
                .map(tournament -> getTournamentEntity(model, tournament))
                .map(tournament -> model)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Tournament doesn't exist")));
    }

    private TournamentEntity getTournamentEntity(Category model, TournamentEntity tournament) {
        tournament.getCategories().stream()
                .filter(categoryEntity -> categoryEntity.getName().equals(model.getName()))
                .findFirst()
                .ifPresentOrElse(
                        categoryEntity -> { throw new RecordAlreadyExistsException(String.format("Category %s is already present", categoryEntity.getName()));},
                        () -> categoryRepository.save(categoryModelToEntity(model, tournament)));
        return tournament;
    }

    private CategoryEntity categoryModelToEntity(Category model, TournamentEntity tournamentFound)
    {
        return CategoryEntity.builder()
                .tournament(tournamentFound)
                .name(model.getName())
                .build();
    }
}

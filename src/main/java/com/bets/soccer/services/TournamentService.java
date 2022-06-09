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

import javax.transaction.Transactional;
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
        var tournamentFound = tournamentRepository.findTournamentByName(model.getName());
        if (tournamentFound.isPresent()) {
            var message = String.format("Tournament %s is already present", model.getName());
            throw new RecordAlreadyExistsException(message);
        }
        tournamentRepository.save(modelToEntity(model));
        return model;
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

    public Category addCategory(Category model)
    {
        var tournamentFound = tournamentRepository.findTournamentByName(model.getTournamentName());
        if(!tournamentFound.isPresent())
            throw new RecordNotFoundException(String.format("Tournament doesn't exist"));

        if(isCategoryPresent(model, tournamentFound))
            throw new RecordAlreadyExistsException(String.format("Category %s is already present", model.getName()));

        var entity = categoryModelToEntity(model, tournamentFound.get());
        categoryRepository.save(entity);
        return model;
    }

    private boolean isCategoryPresent(Category model, Optional<TournamentEntity> tournamentFound) {
        return tournamentFound.get().getCategories()
                .stream().anyMatch(categoryEntity -> categoryEntity.getName().equals(model.getName()));
    }

    private CategoryEntity categoryModelToEntity(Category model, TournamentEntity tournamentFound)
    {
        return CategoryEntity.builder()
                .tournament(tournamentFound)
                .name(model.getName())
                .build();
    }
}

package com.bets.soccer.services;

import com.bets.soccer.entities.CategoryEntity;
import com.bets.soccer.entities.TournamentEntity;
import com.bets.soccer.interfaces.TournamentRepository;
import com.bets.soccer.models.Category;
import com.bets.soccer.models.Tournament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TournamentService
{
    private final TournamentRepository tournamentRepository;

    public String save(Tournament model)
    {
        var tournamentFound = tournamentRepository.findTournamentByName(model.getName());
        if(tournamentFound.isPresent())
            return String.format("Tournament %s is already present", model.getName());

        var entity = modelToEntity(model);
        tournamentRepository.save(entity);
        return "Saved";
    }

    public void addTournament(Tournament model)
    {
        var tournamentFound = tournamentRepository.findTournamentByName(model.getName());
        if (tournamentFound.isPresent()) {
            var message = String.format("Tournament %s is already present", model.getName());
            throw new IllegalStateException(message);
        }
        tournamentRepository.save(modelToEntity(model));
    }

    public List<Tournament> findAll()
    {
        return tournamentRepository.findAll()
                .stream().map(this::entityToModel)
                .collect(Collectors.toList());
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
                .build();
    }
}

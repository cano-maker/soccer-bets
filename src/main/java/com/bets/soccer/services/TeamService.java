package com.bets.soccer.services;

import com.bets.soccer.entities.TeamEntity;
import com.bets.soccer.interfaces.TeamRepository;
import com.bets.soccer.models.Team;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService
{
    private final TeamRepository teamRepository;

    public Optional<Team> save(Team model)
    {
        TeamEntity result = teamRepository.save(modelToEntity(model));
        return Optional.ofNullable(entityToModel(result));
    }

    private TeamEntity modelToEntity(Team model)
    {
        return TeamEntity.builder()
                .name(model.getName())
                .logoPath(model.getLogoPath())
                .build();
    }

    private Team entityToModel(TeamEntity entity)
    {
        return Team.builder()
                .name(entity.getName())
                .logoPath(entity.getLogoPath())
                .build();
    }
}

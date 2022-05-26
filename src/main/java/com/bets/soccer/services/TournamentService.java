package com.bets.soccer.services;

import com.bets.soccer.entities.TournamentEntity;
import com.bets.soccer.interfaces.TournamentRepository;
import com.bets.soccer.models.Tournament;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TournamentService
{

    private final TournamentRepository tournamentRepository;
    public void save(Tournament model)
    {
        tournamentRepository.save(modelToEntity(model));
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
}

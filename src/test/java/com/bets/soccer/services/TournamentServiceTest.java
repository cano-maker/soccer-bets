package com.bets.soccer.services;

import com.bets.soccer.entities.TournamentEntity;
import com.bets.soccer.interfaces.TournamentRepository;
import com.bets.soccer.models.Tournament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TournamentServiceTest
{
    private TournamentRepository tournamentRepository;
    private TournamentService underTest;

    @BeforeEach
    void setUp()
    {
        tournamentRepository = mock(TournamentRepository.class);
        underTest = new TournamentService(tournamentRepository);
    }

    @Test
    void saveShouldReturnFailWhenTournamentIsAlreadyExist()
    {
        Tournament model = Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .logoPath("/home/logo.png")
                .build();
        TournamentEntity entity = modelToEntity(model);

        when(tournamentRepository.findTournamentByName(model.getName())).thenReturn(Optional.of(entity));
        when(tournamentRepository.save(any())).thenReturn(null);
        var result = underTest.save(model);
        assertEquals(String.format("Tournament %s is already present", model.getName()), result);

        verify(tournamentRepository, times(1)).findTournamentByName(model.getName());
        verify(tournamentRepository, times(0)).save(any());
    }

    @Test
    void saveShouldReturnSuccess()
    {
        Tournament model = Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .logoPath("/home/logo.png")
                .build();
        TournamentEntity entity = modelToEntity(model);

        when(tournamentRepository.findTournamentByName(model.getName())).thenReturn(Optional.empty());
        when(tournamentRepository.save(any())).thenReturn(null);
        var result = underTest.save(model);
        assertEquals("Saved", result);

        verify(tournamentRepository, times(1)).findTournamentByName(model.getName());
        verify(tournamentRepository, times(1)).save(any());
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
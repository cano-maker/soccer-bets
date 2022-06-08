package com.bets.soccer.controllers;

import com.bets.soccer.models.*;
import com.bets.soccer.services.TournamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class TournamentControllerTest
{
    private TournamentController underTest;
    private TournamentService tournamentService;
    private LocalDate today;

    @BeforeEach
    void setUp()
    {
        tournamentService = mock(TournamentService.class);
        underTest = new TournamentController(tournamentService);
        today = LocalDate.now();
    }

    @Test
    public void saveTournamentWasSuccessful()
    {

        var model = Tournament.builder()
            .name("Aguila")
            .isActive(true)
            .endDate(today)
            .startDate(today)
            .logoPath("/home/logo.png")
            .build();

        when(tournamentService.add(model)).thenReturn(model);

        var result = underTest.addTournament(model);
        Tournament entity = (Tournament) result.getBody();

        assertEquals(model, entity);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(tournamentService, times(1)).add(model);
    }

    @Test
    public void findAllTournamentWasSuccessful()
    {

        var model = Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(today)
                .startDate(today)
                .logoPath("/home/logo.png")
                .build();

        var teams = List.of(model);


        when(tournamentService.findAll()).thenReturn(teams);

        var result = underTest.findAll();
        List<Tournament> list = (List<Tournament>) result.getBody();

        assertThat(list, hasItem(model));
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(tournamentService, times(1)).findAll();
    }

}
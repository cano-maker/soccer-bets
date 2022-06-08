package com.bets.soccer.controllers;

import com.bets.soccer.models.Team;
import com.bets.soccer.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class TeamControllerTest
{

    private TeamController underTest;
    private TeamService teamService;


    @BeforeEach
    void setUp()
    {
        teamService = mock(TeamService.class);
        underTest = new TeamController(teamService);
    }

    @Test
    public void saveTeamWasSuccessful() throws Exception
    {

        var model = Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();

        when(teamService.add(any())).thenReturn(Optional.of(model));

        var result = underTest.addNewTeam(model);

        assertEquals(model, result);

        verify(teamService, times(1)).add(any());
    }

    @Test
    public void findAllTeamWasSuccessful() throws Exception
    {

        var model = Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();
        var teams = List.of(model);


        when(teamService.findAll()).thenReturn(teams);

        var result = underTest.findAll();

        assertThat(result, hasItem(model));

        verify(teamService, times(1)).findAll();
    }
}
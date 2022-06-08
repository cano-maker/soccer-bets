package com.bets.soccer.controllers;

import com.bets.soccer.models.Team;
import com.bets.soccer.services.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    public void saveTeamWasSuccessful()
    {

        var model = Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();

        when(teamService.add(model)).thenReturn(Optional.of(model));

        var result = underTest.addNewTeam(model);
        Team entity = (Team) result.getBody();

        assertEquals(model, entity);
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(teamService, times(1)).add(model);
    }

    @Test
    public void findAllTeamWasSuccessful()
    {

        var model = Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();
        var teams = List.of(model);


        when(teamService.findAll()).thenReturn(teams);

        var result = underTest.findAll();
        List<Team> list = (List<Team>) result.getBody();

        assertThat(list, hasItem(model));
        assertEquals(HttpStatus.OK, result.getStatusCode());

        verify(teamService, times(1)).findAll();
    }
}
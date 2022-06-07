package com.bets.soccer.services;

import com.bets.soccer.entities.TeamEntity;
import com.bets.soccer.interfaces.TeamRepository;
import com.bets.soccer.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TeamServiceTest
{
    private TeamService underTest;
    private TeamRepository teamRepository;

    @BeforeEach
    void init()
    {
        teamRepository = mock(TeamRepository.class);
        underTest = new TeamService(teamRepository);
    }

    @Test
    void saveTeamWasSuccessful()
    {
        Team model = Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();

        TeamEntity entity = TeamEntity.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();

        when(teamRepository.save(any())).thenReturn(entity);
        var result = underTest.save(model).get();
        assertEquals(result, model);
        verify(teamRepository, times(1)).save(any());
    }

    @Test
    public void findAllTeamWasSuccessful() throws Exception
    {

        var entity = TeamEntity.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();
        var model = Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();
        var teams = List.of(entity);


        when(teamRepository.findAll()).thenReturn(teams);

        var result = underTest.findAll();

        assertThat(result, hasItem(model));

        verify(teamRepository, times(1)).findAll();
    }


}

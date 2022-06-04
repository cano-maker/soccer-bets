package com.bets.soccer.services;

import com.bets.soccer.entities.TeamEntity;
import com.bets.soccer.interfaces.TeamRepository;
import com.bets.soccer.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}

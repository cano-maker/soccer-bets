package com.bets.soccer.services;

import com.bets.soccer.entities.CategoryDetailEntity;
import com.bets.soccer.entities.GameEntity;
import com.bets.soccer.entities.TeamEntity;
import com.bets.soccer.exception.RecordAlreadyExistsException;
import com.bets.soccer.interfaces.TeamRepository;
import com.bets.soccer.models.CategoryDetail;
import com.bets.soccer.models.Game;
import com.bets.soccer.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static com.bets.soccer.enums.Numbers.*;
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
        Team model = getTeamModel();

        TeamEntity entity = getTeamEntity();

        when(teamRepository.save(entity)).thenReturn(entity);
        when(teamRepository.findTeamByName(model.getName())).thenReturn(Optional.empty());

        var result = underTest.add(model).get();

        assertEquals(result, model);

        verify(teamRepository, times(ONE.value())).save(entity);
        verify(teamRepository, times(ONE.value())).findTeamByName(model.getName());
    }



    @Test
    void saveShouldReturnFailWhenTournamentIsAlreadyExistTrowException()
    {
        Team model = getTeamModel();
        TeamEntity entity = getTeamEntityFounded();

        when(teamRepository.findTeamByName(model.getName())).thenReturn(Optional.of(entity));

        RecordAlreadyExistsException result = assertThrows(RecordAlreadyExistsException.class, () -> underTest.add(model));

        assertEquals(String.format("Team %s is already present", model.getName()), result.getMessage());

        verify(teamRepository, times(ONE.value())).findTeamByName(model.getName());
        verify(teamRepository, times(ZERO.value())).save(any());
    }

    @Test
    public void findAllTeamWasSuccessful() throws Exception
    {

        var entity = getTeamEntityFounded();
        var model = getTeamModel();
        var teams = List.of(entity);


        when(teamRepository.findAll()).thenReturn(teams);

        var result = underTest.findAll();

        assertThat(result, hasItem(model));

        verify(teamRepository, times(ONE.value())).findAll();
    }

    private TeamEntity getTeamEntityFounded() {
        return TeamEntity.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .categoriesDetails(Set.of(CategoryDetailEntity.builder()
                        .id(1L)
                        .build()))
                .games(Set.of(GameEntity.builder()
                        .id(1L)
                        .build()))
                .build();
    }

    private TeamEntity getTeamEntity() {
        return TeamEntity.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();
    }

    private Team getTeamModel() {
        return Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .categoriesDetails(Set.of(CategoryDetail.builder()
                        .id(1L)
                        .build()))
                .games(Set.of(Game.builder()
                        .id(1L)
                        .build()))
                .build();
    }

}

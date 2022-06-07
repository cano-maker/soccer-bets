package com.bets.soccer.services;

import com.bets.soccer.entities.CategoryDetailEntity;
import com.bets.soccer.entities.CategoryEntity;
import com.bets.soccer.entities.GameEntity;
import com.bets.soccer.entities.TournamentEntity;
import com.bets.soccer.interfaces.TournamentRepository;
import com.bets.soccer.models.Category;
import com.bets.soccer.models.CategoryDetail;
import com.bets.soccer.models.Game;
import com.bets.soccer.models.Tournament;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class TournamentServiceTest
{
    private TournamentRepository tournamentRepository;
    private TournamentService underTest;

    @BeforeEach
    void init() {
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

    @Test
    void saveShouldReturnFailWhenTournamentIsAlreadyExistTrowException()
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

        IllegalStateException result = assertThrows(IllegalStateException.class, () -> underTest.addTournament(model));

        assertEquals(String.format("Tournament %s is already present", model.getName()), result.getMessage());

        verify(tournamentRepository, times(1)).findTournamentByName(model.getName());
        verify(tournamentRepository, times(0)).save(any());
    }

    @Test
    void findAllIsSuccess()
    {
        Tournament model = Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .logoPath("/home/logo.png")
                .categories(Set.of(Category.builder()
                                .id(1L)
                        .categoriesDetails(Set.of(CategoryDetail.builder()
                                .id(1L)
                                .build()))
                        .games(Set.of(Game.builder()
                                .id(1L)
                                .build()))
                        .build()))
                .build();

        TournamentEntity entity = TournamentEntity.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .logoPath("/home/logo.png")
                .categories(Set.of(CategoryEntity.builder()
                                .id(1L)
                        .categoriesDetails(Set.of(CategoryDetailEntity.builder()
                                .id(1L)
                                .build()))
                        .games(Set.of(GameEntity.builder()
                                .id(1L)
                                .build()))
                        .build()))
                .build();


        List<TournamentEntity> listItems = List.of(entity);


        when(tournamentRepository.findAll()).thenReturn(listItems);

        var result = underTest.findAll();

        assertThat(result, hasItem(model));

        verify(tournamentRepository, times(1)).findAll();
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
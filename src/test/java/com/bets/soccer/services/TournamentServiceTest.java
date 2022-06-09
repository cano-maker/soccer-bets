package com.bets.soccer.services;

import com.bets.soccer.entities.CategoryDetailEntity;
import com.bets.soccer.entities.CategoryEntity;
import com.bets.soccer.entities.GameEntity;
import com.bets.soccer.entities.TournamentEntity;
import com.bets.soccer.exception.RecordAlreadyExistsException;
import com.bets.soccer.exception.RecordNotFoundException;
import com.bets.soccer.interfaces.CategoryRepository;
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
    private CategoryRepository categoryRepository;
    private TournamentService underTest;


    private LocalDate today;

    @BeforeEach
    void init() {
        categoryRepository = mock(CategoryRepository.class);
        tournamentRepository = mock(TournamentRepository.class);
        underTest = new TournamentService(tournamentRepository,
                categoryRepository);
        today = LocalDate.now();
    }

    @Test
    void saveTournamentSuccess()
    {
        Tournament model = getTournamentModel();
        TournamentEntity entity = getTournamentEntity();

        when(tournamentRepository.save(entity)).thenReturn(entity);
        when(tournamentRepository.findTournamentByName(model.getName())).thenReturn(Optional.empty());

        var result = underTest.add(model);

        assertEquals(model, result);

        verify(tournamentRepository, times(1)).save(entity);
        verify(tournamentRepository, times(1)).findTournamentByName(model.getName());

    }


    @Test
    void saveShouldReturnFailWhenTournamentIsAlreadyExistTrowException()
    {
        Tournament model = getTournamentModel();
        TournamentEntity entity = getTournamentEntityFounded();

        when(tournamentRepository.findTournamentByName(model.getName())).thenReturn(Optional.of(entity));

        RecordAlreadyExistsException result = assertThrows(RecordAlreadyExistsException.class, () -> underTest.add(model));

        assertEquals(String.format("Tournament %s is already present", model.getName()), result.getMessage());

        verify(tournamentRepository, times(1)).findTournamentByName(model.getName());
        verify(tournamentRepository, times(0)).save(any());
    }

    @Test
    void findAllIsSuccess()
    {
        Tournament model = getTournamentModel();
        TournamentEntity entity = getTournamentEntityFounded();
        List<TournamentEntity> listItems = List.of(entity);

        when(tournamentRepository.findAll()).thenReturn(listItems);
        var result = underTest.findAll();

        assertThat(result, hasItem(model));
        verify(tournamentRepository, times(1)).findAll();
    }

    @Test
    void saveCategorySuccess()
    {
        var tournamentEntity = getTournamentEntityFounded();
        var model = Category.builder()
                .name("A")
                .tournamentName("Aguila")
                .build();

        var entity = CategoryEntity.builder()
                .name("A")
                .tournament(tournamentEntity)
                .build();

        when(categoryRepository.save(entity)).thenReturn(entity);
        when(tournamentRepository.findTournamentByName(model.getTournamentName())).thenReturn(Optional.of(tournamentEntity));

        var result = underTest.addCategory(model);

        assertEquals(model, result);

        verify(categoryRepository, times(1)).save(entity);
        verify(tournamentRepository, times(1)).findTournamentByName(model.getTournamentName());

    }

    @Test
    void saveCategoryShouldReturnFailWhenTournamentNotFound()
    {
        var tournamentEntity = getTournamentEntityFounded();
        var model = Category.builder()
                .name("A")
                .tournamentName("Aguila")
                .build();

        var entity = CategoryEntity.builder()
                .name("A")
                .tournament(tournamentEntity)
                .build();

        when(tournamentRepository.findTournamentByName(model.getTournamentName())).thenReturn(Optional.empty());

        RecordNotFoundException result = assertThrows(RecordNotFoundException.class, () -> underTest.addCategory(model));

        assertEquals(String.format("Tournament doesn't exist", model.getName()), result.getMessage());

        verify(tournamentRepository, times(1)).findTournamentByName(model.getTournamentName());
        verify(categoryRepository, times(0)).save(entity);
    }

    @Test
    void saveCategoryShouldReturnFailWhenCategoryIsAlreadyExists()
    {
        var tournamentEntity = getTournamentEntityFounded();
        var model = Category.builder()
                .name("B")
                .tournamentName("Aguila")
                .build();

        var entity = CategoryEntity.builder()
                .name("B")
                .tournament(tournamentEntity)
                .build();

        when(tournamentRepository.findTournamentByName(model.getTournamentName())).thenReturn(Optional.of(tournamentEntity));

        RecordAlreadyExistsException result = assertThrows(RecordAlreadyExistsException.class, () -> underTest.addCategory(model));

        assertEquals(String.format("Category %s is already present", model.getName()), result.getMessage());

        verify(tournamentRepository, times(1)).findTournamentByName(model.getTournamentName());
        verify(categoryRepository, times(0)).save(entity);
    }


    private TournamentEntity getTournamentEntityFounded() {
        return TournamentEntity.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(today)
                .startDate(today)
                .logoPath("/home/logo.png")
                .categories(Set.of(CategoryEntity.builder()
                                .name("B")
                        .id(1L)
                        .categoriesDetails(Set.of(CategoryDetailEntity.builder()
                                .id(1L)
                                .build()))
                        .games(Set.of(GameEntity.builder()
                                .id(1L)
                                .build()))
                        .build()))
                .build();
    }

    private TournamentEntity getTournamentEntity() {
        return TournamentEntity.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(today)
                .startDate(today)
                .logoPath("/home/logo.png")
                .build();
    }

    private Tournament getTournamentModel() {
        return Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(today)
                .startDate(today)
                .logoPath("/home/logo.png")
                .categories(Set.of(Category.builder()
                                .name("B")
                        .id(1L)
                        .categoriesDetails(Set.of(CategoryDetail.builder()
                                .id(1L)
                                .build()))
                        .games(Set.of(Game.builder()
                                .id(1L)
                                .build()))
                        .build()))
                .build();
    }


}
package com.bets.soccer.controllers;

import com.bets.soccer.models.Tournament;
import com.bets.soccer.services.TournamentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TournamentControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    @Test
    public void saveTournamentShouldBeSuccessful() throws Exception {
        String url = "/tournament/add";
        String body = "{\n" +
                "    \"name\":\"Aguila\",\n" +
                "    \"startDate\": \"2022-05-26\",\n" +
                "    \"endDate\": \"2022-06-26\",\n" +
                "    \"isActive\": true,\n" +
                "    \"logoPath\": \"/home/logo.png\"\n" +
                "}";
        var model = Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .logoPath("/home/logo.png")
                .build();

        when(tournamentService.save(any())).thenReturn("Saved");
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Saved")));

        verify(tournamentService, times(1)).save(any());
    }

    @Test
    public void saveTournamentShouldBeFail() throws Exception {
        String url = "/tournament/add";
        String body = "{\n" +
                "    \"name\":\"Aguila\",\n" +
                "    \"startDate\": \"2022-05-26\",\n" +
                "    \"endDate\": \"2022-06-26\",\n" +
                "    \"isActive\": true,\n" +
                "    \"logoPath\": \"/home/logo.png\"\n" +
                "}";
        var model = Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .logoPath("/home/logo.png")
                .build();

        when(tournamentService.save(any())).thenReturn("Error saved tournament");
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Error saved tournament")));

        verify(tournamentService, times(1)).save(any());
    }

    @Test
    public void findAllTournament() throws Exception {
        String url = "/tournament/all";
        String body = "{\n" +
                "    \"name\":\"Aguila\",\n" +
                "    \"startDate\": \"2022-05-26\",\n" +
                "    \"endDate\": \"2022-06-26\",\n" +
                "    \"isActive\": true,\n" +
                "    \"logoPath\": \"/home/logo.png\"\n" +
                "}";

        var model = Tournament.builder()
                .name("Aguila")
                .isActive(true)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .logoPath("/home/logo.png")
                .build();

        var listItems = List.of(model);

        when(tournamentService.findAll()).thenReturn(listItems);

        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk());

        verify(tournamentService, times(1)).findAll();
    }

}
package com.bets.soccer.controllers;

import com.bets.soccer.models.Team;
import com.bets.soccer.services.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class TeamControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Test
    public void saveTeamWasSuccessful() throws Exception
    {
        String url = "/team/addTeam";
        String body = "{\n" +
                "    \"name\":\"Nacional\",\n" +
                "    \"logoPath\": \"/home/logo.png\"\n" +
                "}";

        var model = Team.builder()
                .name("Nacional")
                .logoPath("/home/logo.png")
                .build();

        when(teamService.save(any())).thenReturn(Optional.of(model));
        mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andDo(print())
                .andExpect(status().isOk());
        verify(teamService, times(1)).save(any());
    }
}
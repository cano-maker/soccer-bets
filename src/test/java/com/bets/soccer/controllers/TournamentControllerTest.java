//package com.bets.soccer.controllers;
//
//import com.bets.soccer.entities.TournamentEntity;
//import com.bets.soccer.interfaces.TournamentRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest()
//class TournamentControllerTest
//{
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TournamentRepository tournamentRepository;
//
//    @Test
//    public void saveTournamentShouldBeSuccessful() throws Exception {
//        var entity = TournamentEntity.builder().build();
//        when(tournamentRepository.save(any())).thenReturn(entity);
//        mockMvc.perform(post("/tournament/add?name=Aguila&logoPath=/store/pipi.png"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Save")));
//    }
//
//}
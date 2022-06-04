package com.bets.soccer.controllers;

import com.bets.soccer.models.Tournament;
import com.bets.soccer.services.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping(path = "/tournament")
@AllArgsConstructor
public class TournamentController
{
    private final TournamentService tournamentService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewTournament(@RequestBody Tournament model){
        return tournamentService.save(model);
    }

    @PostMapping(path="/addTournament")
    public void addTournament(@RequestBody Tournament model){
       tournamentService.addTournament(model);
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Tournament> findAll()
    {
        return tournamentService.findAll();
    }


}

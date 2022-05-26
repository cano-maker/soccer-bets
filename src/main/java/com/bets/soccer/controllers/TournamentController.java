package com.bets.soccer.controllers;


import com.bets.soccer.interfaces.TournamentRepository;
import com.bets.soccer.models.Tournament;
import com.bets.soccer.services.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/tournament")
@AllArgsConstructor
public class TournamentController
{
    private final TournamentService tournamentService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewTournament(@RequestBody Tournament model){
        tournamentService.save(model);
        return "Saved";
    }

}

package com.bets.soccer.controllers;

import com.bets.soccer.models.Category;
import com.bets.soccer.models.Tournament;
import com.bets.soccer.services.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addTournament(@RequestBody Tournament model)
    {
        Tournament result = tournamentService.add(model);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(path="/all")
    public ResponseEntity findAll()
    {
        List<Tournament> result = tournamentService.findAll();
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @PostMapping(path = "/category/add")
    public ResponseEntity addCategory(@RequestBody Category model)
    {
        Category result = tournamentService.addCategory(model);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}

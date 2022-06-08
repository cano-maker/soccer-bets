package com.bets.soccer.controllers;

import com.bets.soccer.models.Team;
import com.bets.soccer.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/team")
@AllArgsConstructor
public class TeamController
{
    private final TeamService teamService;

    @PostMapping(path = "/addTeam")
    public ResponseEntity addNewTeam(@RequestBody Team model)
    {
        var result = teamService.add(model)
                .orElse(Team.builder().build());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity findAll()
    {
        return new ResponseEntity(teamService.findAll(), HttpStatus.OK);
    }
}

package com.bets.soccer.controllers;

import com.bets.soccer.models.Team;
import com.bets.soccer.services.TeamService;
import lombok.AllArgsConstructor;
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
    public @ResponseBody Team addNewTeam(@RequestBody Team model)
    {
        return teamService.add(model)
                .orElse(Team.builder().build());
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<Team> findAll()
    {
        return teamService.findAll();
    }
}

package com.bets.soccer.controllers;

import com.bets.soccer.models.Team;
import com.bets.soccer.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/team")
@AllArgsConstructor
public class TeamController
{
    private final TeamService teamService;

    @PostMapping(path = "/addTeam")
    public @ResponseBody Team addNewTeam(@RequestBody Team model)
    {
        return teamService.save(model)
                .orElse(Team.builder().build());
    }
}

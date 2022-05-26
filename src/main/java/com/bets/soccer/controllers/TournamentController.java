package com.bets.soccer.controllers;


import com.bets.soccer.entities.TournamentEntity;
import com.bets.soccer.interfaces.TournamentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;


@Controller
@RequestMapping(path = "/tournament")
@AllArgsConstructor
public class TournamentController
{
    @Autowired
    private final TournamentRepository tournamentRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewTournament(@RequestParam String name
    , @RequestParam String logoPath){
        TournamentEntity entity = TournamentEntity.builder()
                .name(name)
                .logoPath(logoPath)
                .startDate(LocalDate.now())
                .EndDate(LocalDate.now())
                .isActive(Boolean.TRUE)
                .build();
        tournamentRepository.save(entity);
        return "Saved";
    }

}

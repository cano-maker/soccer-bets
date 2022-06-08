package com.bets.soccer.interfaces;

import com.bets.soccer.entities.TeamEntity;
import com.bets.soccer.entities.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer>
{
    @Query("SELECT t FROM Team t WHERE t.name = ?1")
    Optional<TeamEntity> findTeamByName(String name);
}

package com.bets.soccer.interfaces;

import com.bets.soccer.entities.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity, Integer>
{
    @Query("SELECT t FROM Tournament t WHERE t.name = ?1")
    Optional<TournamentEntity> findTournamentByName(String name);
}

package com.bets.soccer.interfaces;

import com.bets.soccer.entities.TournamentEntity;
import org.springframework.data.repository.CrudRepository;

public interface TournamentRepository extends CrudRepository<TournamentEntity, Integer>
{

}

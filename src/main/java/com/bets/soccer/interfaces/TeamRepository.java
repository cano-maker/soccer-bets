package com.bets.soccer.interfaces;

import com.bets.soccer.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer>
{

}

package com.bets.soccer.interfaces;

import com.bets.soccer.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>
{
    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    Optional<CategoryEntity> findCategoryByName(String name);
}

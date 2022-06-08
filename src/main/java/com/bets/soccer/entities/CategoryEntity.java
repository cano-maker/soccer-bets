package com.bets.soccer.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Category")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JoinColumn(name="tournament_id", nullable=false)
    private TournamentEntity tournament;

    @OneToMany(mappedBy="category")
    private Set<GameEntity> games;

    @OneToMany(mappedBy="category")
    private Set<CategoryDetailEntity> categoriesDetails;

}

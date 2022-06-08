package com.bets.soccer.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity(name = "Team")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TeamEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String logoPath;

    @OneToMany(mappedBy="team")
    private Set<CategoryDetailEntity> categoriesDetails;

    @OneToMany(mappedBy="team")
    private Set<GameEntity> games;
}

package com.pokemoncards.model;

import java.util.Set;

import lombok.Data;

@Data
public class PokemonCardRequest {
    private String name;
    private int hp;
    private String rarity;
    private Long trainerId;
    private Set<Long> typeIds;
}

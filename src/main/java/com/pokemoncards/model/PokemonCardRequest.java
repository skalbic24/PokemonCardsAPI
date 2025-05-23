package com.pokemoncards.model;

import java.util.List;


import lombok.Data;

//incoming requests(POST/PUT)

@Data
public class PokemonCardRequest {
    private String name;
    private int hp;
    private String rarity;
    private String imageUrl;
    private Long trainerId;
    private List<Long> typeIds;
}

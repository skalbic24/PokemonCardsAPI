package com.pokemoncards.model;

import java.util.Set;
import lombok.Data;
//out going response
@Data
public class PokemonCardResponse {
    private Long cardId;
    private String name;
    private int hp;
    private String rarity;
    private String imageUrl;
    private Set<PokemonTypeDTO> types;
}

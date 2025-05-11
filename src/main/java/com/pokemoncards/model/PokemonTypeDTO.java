package com.pokemoncards.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PokemonTypeDTO {
    private Long typeId;
    private String typeName;
}

package com.pokemoncards.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import com.pokemoncards.entity.PokemonCard;

public interface PokemonCardDao extends JpaRepository<PokemonCard, Long> {
	List<PokemonCard> findByName(String name);
;
}

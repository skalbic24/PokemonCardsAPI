package com.pokemoncards.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pokemoncards.entity.PokemonCard;

public interface PokemonCardDao extends JpaRepository<PokemonCard, Long> {
	Optional<PokemonCard> findByName(String name);
}

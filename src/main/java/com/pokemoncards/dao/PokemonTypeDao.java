package com.pokemoncards.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pokemoncards.entity.PokemonType;

public interface PokemonTypeDao extends JpaRepository<PokemonType, Long> {
	Optional<PokemonType> findByTypeName(String typeName);

}

package com.pokemoncards.service;

import com.pokemoncards.dao.PokemonTypeDao;
import com.pokemoncards.entity.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonTypeService {

    @Autowired
    private PokemonTypeDao pokemonTypeDao;
    
    public Optional<PokemonType> findByName(String name) {
        return pokemonTypeDao.findByTypeName(name);
    }

    public PokemonType saveType(PokemonType type) {
        return pokemonTypeDao.save(type);
    }

    public List<PokemonType> getAllTypes() {
        return pokemonTypeDao.findAll();
    }

    public PokemonType getTypeById(Long id) {
        return pokemonTypeDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon type not found with id: " + id));
    }

    public PokemonType updateType(Long id, PokemonType updatedType) {
        PokemonType type = getTypeById(id);
        type.setTypeName(updatedType.getTypeName());
        return pokemonTypeDao.save(type);
    }

    public void deleteType(Long id) {
        pokemonTypeDao.deleteById(id);
    }
}

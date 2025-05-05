package com.pokemoncards.controller;

import com.pokemoncards.entity.PokemonType;
import com.pokemoncards.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
public class PokemonTypeController {

    @Autowired
    private PokemonTypeService pokemonTypeService;

    @PostMapping
    public PokemonType createType(@RequestBody PokemonType type) {
        return pokemonTypeService.saveType(type);
    }

    @GetMapping
    public List<PokemonType> getAllTypes() {
        return pokemonTypeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public PokemonType getType(@PathVariable Long id) {
        return pokemonTypeService.getTypeById(id);
    }

    @PutMapping("/{id}")
    public PokemonType updateType(@PathVariable Long id, @RequestBody PokemonType type) {
        return pokemonTypeService.updateType(id, type);
    }

    @DeleteMapping("/{id}")
    public void deleteType(@PathVariable Long id) {
        pokemonTypeService.deleteType(id);
    }
}

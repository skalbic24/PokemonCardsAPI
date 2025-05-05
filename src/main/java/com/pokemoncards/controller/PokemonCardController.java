package com.pokemoncards.controller;

import com.pokemoncards.entity.PokemonCard;
import com.pokemoncards.service.PokemonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class PokemonCardController {

    @Autowired
    private PokemonCardService pokemonCardService;

    @PostMapping
    public PokemonCard createCard(@RequestBody PokemonCard card) {
        return pokemonCardService.saveCard(card);
    }

    @GetMapping
    public List<PokemonCard> getAllCards() {
        return pokemonCardService.getAllCards();
    }

    @GetMapping("/{id}")
    public PokemonCard getCard(@PathVariable Long id) {
        return pokemonCardService.getCardById(id);
    }

    @PutMapping("/{id}")
    public PokemonCard updateCard(@PathVariable Long id, @RequestBody PokemonCard card) {
        return pokemonCardService.updateCard(id, card);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        pokemonCardService.deleteCard(id);
    }
}

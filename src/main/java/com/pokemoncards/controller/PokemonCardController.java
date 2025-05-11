package com.pokemoncards.controller;

import com.pokemoncards.entity.PokemonCard;
import com.pokemoncards.model.PokemonCardRequest;
import com.pokemoncards.model.PokemonCardResponse;
import com.pokemoncards.service.PokemonCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/cards")
public class PokemonCardController {

    @Autowired
    private PokemonCardService pokemonCardService;

    @PostMapping
    public PokemonCardResponse createCard(@RequestBody PokemonCardRequest request) {
        PokemonCard card = pokemonCardService.createCardWithTypes(request);
        return pokemonCardService.mapToResponse(card);
    }

    @GetMapping
    public List<PokemonCardResponse> getAllCards() {
        return pokemonCardService.getAllCards().stream()
            .map(pokemonCardService::mapToResponse)
            .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public PokemonCardResponse getCard(@PathVariable Long id) {
        PokemonCard card = pokemonCardService.getCardById(id);
        return pokemonCardService.mapToResponse(card);
    }

    @PutMapping("/{id}")
    public PokemonCardResponse updateCard(@PathVariable Long id, @RequestBody PokemonCardRequest request) {
        PokemonCard updated = pokemonCardService.updateCard(id, request);
        return pokemonCardService.mapToResponse(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        pokemonCardService.deleteCard(id);
    }
}

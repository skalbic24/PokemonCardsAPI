package com.pokemoncards.service;

import com.pokemoncards.dao.PokemonCardDao;
import com.pokemoncards.entity.PokemonCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonCardService {

    @Autowired
    private PokemonCardDao pokemonCardDao;
    
    public Optional<PokemonCard> findByName(String name) {
        return pokemonCardDao.findByName(name);
    }

    public PokemonCard saveCard(PokemonCard card) {
        return pokemonCardDao.save(card);
    }

    public List<PokemonCard> getAllCards() {
        return pokemonCardDao.findAll();
    }

    public PokemonCard getCardById(Long id) {
        return pokemonCardDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon card not found with id: " + id));
    }

    public PokemonCard updateCard(Long id, PokemonCard updatedCard) {
        PokemonCard card = getCardById(id);
        card.setName(updatedCard.getName());
        card.setHp(updatedCard.getHp());
        card.setRarity(updatedCard.getRarity());
        card.setTrainer(updatedCard.getTrainer());
        card.setTypes(updatedCard.getTypes());
        return pokemonCardDao.save(card);
    }

    public void deleteCard(Long id) {
        pokemonCardDao.deleteById(id);
    }
}

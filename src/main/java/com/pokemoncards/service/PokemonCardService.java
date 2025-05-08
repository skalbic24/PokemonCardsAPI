package com.pokemoncards.service;

import com.pokemoncards.dao.PokemonCardDao;
import com.pokemoncards.dao.PokemonTypeDao;
import com.pokemoncards.dao.TrainerDao;
import com.pokemoncards.entity.PokemonCard;
import com.pokemoncards.entity.PokemonType;
import com.pokemoncards.entity.Trainer;
import com.pokemoncards.model.PokemonCardRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonCardService {

    @Autowired
    private PokemonCardDao cardRepo;

    @Autowired
    private PokemonTypeDao typeRepo;
    
    @Autowired
    private TrainerDao trainerDao;

    public PokemonCard createCardWithTypes(PokemonCardRequest request) {
        PokemonCard card = new PokemonCard();
        card.setName(request.getName());
        card.setHp(request.getHp());
        card.setRarity(request.getRarity());

     // Fetch and assign types
        Set<PokemonType> types = new HashSet<>();
        for (Long typeId : request.getTypeIds()) {
            PokemonType type = typeRepo.findById(typeId)
                    .orElseThrow(() -> new RuntimeException("Type not found with ID: " + typeId));
            types.add(type);
        }

        card.setTypes(types);
        
     // Fetch and assign trainer
        if (request.getTrainerId() != null) {
            Trainer trainer = trainerDao.findById(request.getTrainerId())
                    .orElseThrow(() -> new RuntimeException("Trainer not found with ID: " + request.getTrainerId()));
            card.setTrainer(trainer);
        }
        
        return cardRepo.save(card);
    }

    public Optional<PokemonCard> findByName(String name) {
        return cardRepo.findByName(name);
    }

    public PokemonCard saveCard(PokemonCard card) {
        return cardRepo.save(card);
    }

    public List<PokemonCard> getAllCards() {
        return cardRepo.findAll();
    }

    public PokemonCard getCardById(Long id) {
        return cardRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon card not found with id: " + id));
    }

    public PokemonCard updateCard(Long id, PokemonCard updatedCard) {
        PokemonCard card = getCardById(id);
        card.setName(updatedCard.getName());
        card.setHp(updatedCard.getHp());
        card.setRarity(updatedCard.getRarity());
        card.setTrainer(updatedCard.getTrainer());
        card.setTypes(updatedCard.getTypes());
        return cardRepo.save(card);
    }

    public void deleteCard(Long id) {
        cardRepo.deleteById(id);
    }
}

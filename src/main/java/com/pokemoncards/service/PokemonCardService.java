package com.pokemoncards.service;

import com.pokemoncards.dao.PokemonCardDao;
import com.pokemoncards.dao.PokemonTypeDao;
import com.pokemoncards.dao.TrainerDao;
import com.pokemoncards.entity.PokemonCard;
import com.pokemoncards.entity.PokemonType;
import com.pokemoncards.entity.Trainer;
import com.pokemoncards.model.PokemonCardRequest;
import com.pokemoncards.model.PokemonCardResponse;
import com.pokemoncards.model.PokemonTypeDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<PokemonCard> findByName(String name) {
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

    public PokemonCard updateCard(Long id, PokemonCardRequest request) {
        PokemonCard card = getCardById(id); // fetch existing

        card.setName(request.getName());
        card.setHp(request.getHp());
        card.setRarity(request.getRarity());
        card.setImageUrl(request.getImageUrl());

        //  Set trainer from ID
        if (request.getTrainerId() != null) {
            Trainer trainer = trainerDao.findById(request.getTrainerId())
                .orElseThrow(() -> new RuntimeException("Trainer not found with ID: " + request.getTrainerId()));
            card.setTrainer(trainer);
        }

        //  Set types from ID list
        Set<PokemonType> types = new HashSet<>();
        for (Long typeId : request.getTypeIds()) {
            PokemonType type = typeRepo.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Type not found with ID: " + typeId));
            types.add(type);
        }
        card.setTypes(types);

        return cardRepo.save(card);
    }


    public void deleteCard(Long id) {
        cardRepo.deleteById(id);
    }
    /**
     * Converts a PokemonCard entity into a simplified DTO (PokemonCardResponse)
     * containing only fields needed for the client, including type names.
     */
    public PokemonCardResponse mapToResponse(PokemonCard card) {
        PokemonCardResponse response = new PokemonCardResponse();
        response.setCardId(card.getCardId());
        response.setName(card.getName());
        response.setHp(card.getHp());
        response.setRarity(card.getRarity());
        response.setImageUrl(card.getImageUrl());

        Set<PokemonTypeDTO> typeDTOs = card.getTypes().stream()
            .map(type -> new PokemonTypeDTO(type.getTypeId(), type.getTypeName()))
            .collect(Collectors.toSet());

        response.setTypes(typeDTOs);
        return response;
    }
}

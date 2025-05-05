package com.pokemoncards.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemoncards.entity.*;
import com.pokemoncards.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired private TrainerService trainerService;
    @Autowired private PokemonTypeService typeService;
    @Autowired private PokemonCardService cardService;

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Load Trainers
        InputStream trainerStream = new ClassPathResource("trainers.json").getInputStream();
        List<Trainer> trainers = mapper.readValue(trainerStream, new TypeReference<>() {});
        Map<String, Trainer> savedTrainers = new HashMap<>();
        for (Trainer trainer : trainers) {
            Trainer saved;
            Optional<Trainer> existing = trainerService.findByName(trainer.getTrainerName());
            if (existing.isPresent()) {
                saved = existing.get();
            } else {
                saved = trainerService.saveTrainer(trainer);
            }
            savedTrainers.put(saved.getTrainerName(), saved);
        }

        // Load Types
        InputStream typeStream = new ClassPathResource("pokemon_types.json").getInputStream();
        List<PokemonType> types = mapper.readValue(typeStream, new TypeReference<>() {});
        Map<String, PokemonType> savedTypes = new HashMap<>();
        for (PokemonType type : types) {
            PokemonType saved;
            Optional<PokemonType> existing = typeService.findByName(type.getTypeName());
            if (existing.isPresent()) {
                saved = existing.get();
            } else {
                saved = typeService.saveType(type);
            }
            savedTypes.put(saved.getTypeName(), saved);
        }

        // Load Cards
        InputStream cardStream = new ClassPathResource("pokemon_cards.json").getInputStream();
        List<Map<String, Object>> cards = mapper.readValue(cardStream, new TypeReference<>() {});
        for (Map<String, Object> cardMap : cards) {
            String name = (String) cardMap.get("name");
            Optional<PokemonCard> existingCard = cardService.findByName(name);
            if (existingCard.isPresent()) continue; // skip duplicates

            PokemonCard card = new PokemonCard();
            card.setName(name);
            card.setHp((Integer) cardMap.get("hp"));
            card.setRarity((String) cardMap.get("rarity"));

            // Link Trainer
            String trainerName = (String) cardMap.get("trainerName");
            card.setTrainer(savedTrainers.get(trainerName));

            // Link Types
            List<String> typeNames = mapper.convertValue(cardMap.get("types"), new TypeReference<List<String>>() {});
            Set<PokemonType> cardTypes = new HashSet<>();
            if (typeNames != null) {
                for (String t : typeNames) {
                	PokemonType type = savedTypes.get(t);
                    if (type != null) {
                        cardTypes.add(type);
                }
            }
            card.setTypes(cardTypes);
            cardService.saveCard(card);
        }
    }
}
}

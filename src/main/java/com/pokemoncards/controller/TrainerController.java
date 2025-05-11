package com.pokemoncards.controller;

import com.pokemoncards.dao.TrainerDao;
import com.pokemoncards.entity.Trainer;
import com.pokemoncards.model.PokemonCardResponse;
import com.pokemoncards.service.PokemonCardService;
import com.pokemoncards.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
		
	@Autowired
	private TrainerDao trainerDao;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private PokemonCardService pokemonCardService;
    
    @PostMapping
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerService.saveTrainer(trainer);
    }

    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @GetMapping("/{id}")
    public Trainer getTrainer(@PathVariable Long id) {
        return trainerService.getTrainerById(id);
    }
    
    @GetMapping("/{id}/cards")
    public List<PokemonCardResponse> getCardsByTrainer(@PathVariable Long id) {
        Trainer trainer = trainerDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Trainer not found with id: " + id));
        return trainer.getCards().stream()
            .map(pokemonCardService::mapToResponse)
            .toList();
    }

    @PutMapping("/{id}")
    public Trainer updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        return trainerService.updateTrainer(id, trainer);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
    }
}

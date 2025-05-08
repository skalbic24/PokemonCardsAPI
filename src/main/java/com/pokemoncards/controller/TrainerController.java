package com.pokemoncards.controller;

import com.pokemoncards.dao.TrainerDao;
import com.pokemoncards.entity.PokemonCard;
import com.pokemoncards.entity.Trainer;
import com.pokemoncards.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
	
	@Autowired
	private TrainerDao trainerDao;

    @Autowired
    private TrainerService trainerService;

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
    public List<PokemonCard> getCardsByTrainer(@PathVariable Long id) {
        Trainer trainer = trainerDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Trainer not found with id: " + id));
        return new ArrayList<>(trainer.getCards());
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

package com.pokemoncards.service;

import com.pokemoncards.dao.TrainerDao;
import com.pokemoncards.entity.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {

    @Autowired
    private TrainerDao trainerDao;

    public Trainer saveTrainer(Trainer trainer) {
        return trainerDao.save(trainer);
    }

    public Optional<Trainer> findByName(String name) {
        return trainerDao.findByTrainerName(name);
    }

    public List<Trainer> getAllTrainers() {
        return trainerDao.findAll();
    }

    public Trainer getTrainerById(Long id) {
        return trainerDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer not found with id: " + id));
    }

    public Trainer updateTrainer(Long id, Trainer updatedTrainer) {
        Trainer trainer = getTrainerById(id);
        trainer.setTrainerName(updatedTrainer.getTrainerName());
        trainer.setRegion(updatedTrainer.getRegion());
        return trainerDao.save(trainer);
    }

    public void deleteTrainer(Long id) {
        trainerDao.deleteById(id);
    }
}

package com.pokemoncards.dao;

import com.pokemoncards.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerDao extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByTrainerName(String trainerName);
}

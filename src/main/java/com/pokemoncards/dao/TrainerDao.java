package com.pokemoncards.dao;

import com.pokemoncards.entity.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerDao extends JpaRepository<Trainer, Long> {
    List<Trainer> findByTrainerName(String trainerName);
    

}

package com.pokemoncards.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Data
public class PokemonCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String name;
    private int hp;
    private String rarity;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference("trainer-cards") // <-- link to Trainer's reference
    private Trainer trainer;

    @ManyToMany
    @JoinTable(
        name = "pokemon_card_type",
        joinColumns = @JoinColumn(name = "card_id"),
        inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    @JsonManagedReference("card-types") 
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<PokemonType> types = new HashSet<>();
    
}


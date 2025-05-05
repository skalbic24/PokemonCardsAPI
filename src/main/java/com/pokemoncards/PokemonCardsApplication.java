package com.pokemoncards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "com.pokemoncards")
//@EnableJpaRepositories(basePackages = "com.pokemoncards.dao")
//@EntityScan(basePackages = "com.pokemoncards.entity")
public class PokemonCardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonCardsApplication.class, args);
    }
}

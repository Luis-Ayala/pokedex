package com.bankaya.deliverable.pokedex.pokeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bankaya.deliverable")
public class PokeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokeApplication.class);
    }
}


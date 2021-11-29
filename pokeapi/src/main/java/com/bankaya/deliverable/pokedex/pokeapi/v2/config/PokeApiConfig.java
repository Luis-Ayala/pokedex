package com.bankaya.deliverable.pokedex.pokeapi.v2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration class for PokeAPI project.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class PokeApiConfig {
    private List<String> pokeapiService = new ArrayList<>();
    public final static String URL_SEPARATOR = "/";

    public List<String> getPokeapiService() {
        return pokeapiService;
    }

    public void setPokeapiService(List<String> pokeapiService) {
        this.pokeapiService = pokeapiService;
    }
}


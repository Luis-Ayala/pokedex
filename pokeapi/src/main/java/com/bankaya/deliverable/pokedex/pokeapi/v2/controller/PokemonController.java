package com.bankaya.deliverable.pokedex.pokeapi.v2.controller;

import com.bankaya.deliverable.pokedex.pokeapi.v2.service.PokeApiService;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonAbility;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonHeldItem;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Controller class for PokeAPI project
 */
@Controller
public class PokemonController {

    private final PokeApiService pokeApiService;

    public PokemonController(final PokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    public List<PokemonAbility> getPokemonAbilities(final String pokemon) throws PokedexException {
        return pokeApiService.getAbilities(pokemon);
    }

    public Integer getPokemonBaseExperience(final String pokemon) throws PokedexException {
        return pokeApiService.getBaseExperience(pokemon);
    }

    public Integer getPokemonId(final String pokemon) throws PokedexException {
        return pokeApiService.getId(pokemon);
    }

    public String getPokemonName(final String pokemon) throws PokedexException {
        return pokeApiService.getName(pokemon);
    }

    public String getLocationAreaEncounters(final String pokemon) throws PokedexException {
        return pokeApiService.getLocationAreaEncounters(pokemon);
    }

    public List<PokemonHeldItem> getHeldItems(final String pokemon) throws PokedexException {
        return pokeApiService.getHeldItems(pokemon);
    }
}


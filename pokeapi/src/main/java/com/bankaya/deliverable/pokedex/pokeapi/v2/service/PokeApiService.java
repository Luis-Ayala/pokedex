package com.bankaya.deliverable.pokedex.pokeapi.v2.service;

import com.bankaya.deliverable.pokedex.pokeapi.v2.util.ExceptionUtil;
import com.bankaya.deliverable.pokedex.pokeapi.v2.util.Mapper;
import com.bankaya.deliverable.pokedex.pokeapi.v2.util.PokeApiUtil;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonAbility;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonHeldItem;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonSection;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service class for PokeAPI project
 */
@Service
public class PokeApiService {
    private final static Logger LOG = LoggerFactory.getLogger(PokeApiService.class);

    private final PokeApiUtil pokeApiUtil;

    public PokeApiService(final PokeApiUtil pokeApiUtil) {
        this.pokeApiUtil = pokeApiUtil;
    }

    /**
     * Gets all abilities for a given Pokemon
     * @param pokemon Pokemon name
     * @return A list of Pokemon's abilities
     * @throws PokedexException
     */
    public List<PokemonAbility> getAbilities(final String pokemon) throws PokedexException {
        LOG.debug("Starting getAbilities with: {}", pokemon);
        final List<PokemonAbility> abilities;
        try {
            final JsonNode node = pokeApiUtil.getPokemonInfo(pokemon, PokemonSection.ABILITIES);
            abilities = new ArrayList<>(Arrays.asList(Mapper.map(node, new PokemonAbility[]{}).getData()));
        } catch (Exception e) {
            throw ExceptionUtil.of(e);
        }
        LOG.debug("getAbilities finished with: {}", abilities);
        return abilities;
    }

    /**
     * Gets the Pokemon base experience
     * @param pokemon Pokemon name
     * @return The Pokemon's base experience
     * @throws PokedexException
     */
    public Integer getBaseExperience(final String pokemon) throws PokedexException {
        LOG.debug("Starting getBaseExperience with: {}", pokemon);
        int baseExperience;
        try {
            final JsonNode node = pokeApiUtil.getPokemonInfo(pokemon, PokemonSection.BASE_EXPERIENCE);
            baseExperience = node.asInt();
        } catch (Exception e) {
            throw ExceptionUtil.of(e);
        }
        LOG.debug("getBaseExperience finished with: {}", baseExperience);
        return baseExperience;
    }

    /**
     * Gets all Poekemon's held items
     * @param pokemon Pokemon name
     * @return A list of Pokemon's held items
     * @throws PokedexException
     */
    public List<PokemonHeldItem> getHeldItems(final String pokemon) throws PokedexException {
        LOG.debug("Starting getHeldItems with: {}", pokemon);
        final List<PokemonHeldItem> heldItems;
        try {
            final JsonNode node = pokeApiUtil.getPokemonInfo(pokemon, PokemonSection.HELD_ITEMS);
            heldItems = new ArrayList<>(Arrays.asList(Mapper.map(node, new PokemonHeldItem[]{}).getData()));
        } catch (Exception e) {
            throw ExceptionUtil.of(e);
        }
        LOG.debug("getHeldItems finished with: {}", heldItems);
        return heldItems;
    }

    /**
     * Gets the Pokemon id
     * @param pokemon Pokemon name
     * @return The Pokemon's id
     * @throws PokedexException
     */
    public Integer getId(final String pokemon) throws PokedexException {
        LOG.debug("Starting getId with: {}", pokemon);
        int id;
        try {
            final JsonNode node = pokeApiUtil.getPokemonInfo(pokemon, PokemonSection.ID);
            id = node.asInt();
        } catch (Exception e) {
            throw ExceptionUtil.of(e);
        }
        LOG.debug("getId finished with: {}", id);
        return id;
    }

    /**
     * Gets the Pokemon's name
     * @param pokemon Pokemon name
     * @return The Pokemon's name
     * @throws PokedexException
     */
    public String getName(final String pokemon) throws PokedexException {
        LOG.debug("Starting getName with: {}", pokemon);
        String name;
        try {
            final JsonNode node = pokeApiUtil.getPokemonInfo(pokemon, PokemonSection.NAME);
            name = node.asText();
        } catch (Exception e) {
            throw ExceptionUtil.of(e);
        }
        LOG.debug("getId getName with: {}", name);
        return name;
    }

    /**
     * Gets all Pokemon's location area encounters
     * @param pokemon Pokemon name
     * @return A Pokemon's location area encounters url
     * @throws PokedexException
     */
    public String getLocationAreaEncounters(final String pokemon) throws PokedexException {
        LOG.debug("Starting getLocationAreaEncounters with: {}", pokemon);
        String locationAreaEncounters;
        try {
            final JsonNode node = pokeApiUtil.getPokemonInfo(pokemon, PokemonSection.LOCATION_AREA_ENCOUNTERS);
            locationAreaEncounters = node.asText();
        } catch (Exception e) {
            throw ExceptionUtil.of(e);
        }
        LOG.debug("getId getLocationAreaEncounters with: {}", locationAreaEncounters);
        return locationAreaEncounters;
    }
}


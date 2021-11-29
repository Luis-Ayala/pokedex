package com.bankaya.deliverable.pokedex.pokeapi.v2.util;

import com.bankaya.deliverable.pokedex.pokeapi.v2.config.PokeApiConfig;
import com.bankaya.deliverable.pokedex.pokeapi.v2.service.PokeApiService;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonSection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiUtil {
    private final static Logger LOG = LoggerFactory.getLogger(PokeApiService.class);

    private final String pokeApiURI;
    private final RestTemplate restTemplate;

    public PokeApiUtil(final PokeApiConfig pokeApiConfig,
                       final RestTemplateBuilder builder) {
        this.pokeApiURI = String.join(PokeApiConfig.URL_SEPARATOR, pokeApiConfig.getPokeapiService());
        this.restTemplate = builder.build();
    }

    /**
     * Gets the data for the given section
     * @param pokemon Pokemon name
     * @param node The section to return
     * @return A JsonNode with the section information
     * @throws JsonProcessingException
     */
    public JsonNode getPokemonInfo(final String pokemon, final PokemonSection node) throws JsonProcessingException {
        LOG.debug("getPokemonInfo with pokemon: [{}], node: [{}]", pokemon, node);
        final ResponseEntity<String> entity = callPokemonApi(pokemon);
        final JsonNode json = parseStringToJson(entity.getBody());
        LOG.debug("getPokemonInfo response node: {}", json);
        return json.get(node.getName());
    }

    /**
     * Calls the PokeAPI service
     * @param pokemon Pokemon name
     * @return A response entity of type String
     */
    private ResponseEntity<String> callPokemonApi(final String pokemon) {
        return restTemplate.getForEntity(String.join(PokeApiConfig.URL_SEPARATOR, this.pokeApiURI, pokemon),
                String.class);
    }

    /**
     * Extract the section from the PokeAPI response
     * @param jsonString PokeAPI response
     * @return A JsonNode object
     * @throws JsonProcessingException
     */
    private JsonNode parseStringToJson(final String jsonString) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonString);
    }
}


package com.bankaya.deliverable.pokedex.pokeapp.endpoint;

import com.bankaya.deliverable.pokedex.pokeapi.v2.controller.PokemonController;
import com.bankaya.deliverable.pokedex.pokeapp.data.repository.RequestRepository;
import com.bankaya.deliverable.pokedex.pokeapp.util.EndPointUtils;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonAbility;
import com.bankaya.deliverable.pokedex.ws.Abilities;
import com.bankaya.deliverable.pokedex.ws.GetPokemonAbilitiesRequest;
import com.bankaya.deliverable.pokedex.ws.GetPokemonAbilitiesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Endpoint
public class PokemonAbilitiesEndPoint {
    private static final Logger LOG = LoggerFactory.getLogger(PokemonAbilitiesEndPoint.class);
    
    private static final String NAMESPACE_URI = "http://bankaya.com/deliverable/pokedex/ws";
    private static final String methodName = "getPokemonAbilities";

    private final PokemonController pokemonController;
    private final RequestRepository requestRepository;
    private final HttpServletRequest httpRequest;

    public PokemonAbilitiesEndPoint(PokemonController pokemonController, RequestRepository requestRepository,
                                    HttpServletRequest httpRequest) {
        this.pokemonController = pokemonController;
        this.requestRepository = requestRepository;
        this.httpRequest = httpRequest;
    }

    /**
     * Entry method for getPokemonAbilitiesRequest
     * @param request GetPokemonAbilitiesRequest object
     * @return
     * @throws PokedexException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonAbilitiesRequest")
    @ResponsePayload
    public GetPokemonAbilitiesResponse getPokemonAbilities(@RequestPayload GetPokemonAbilitiesRequest request) throws PokedexException {
        LOG.debug("Starting getPokemonAbilities");
        EndPointUtils.saveRequest(requestRepository, httpRequest.getRemoteAddr(), methodName, request.getPokemon());
        GetPokemonAbilitiesResponse response = new GetPokemonAbilitiesResponse();
        final List<PokemonAbility> pokemonAbilities = pokemonController.getPokemonAbilities(request.getPokemon());
        response.getAbilities().addAll(parseResponse(pokemonAbilities));
        LOG.debug("getPokemonAbilities finished.");
        return response;
    }

    /**
     * Map the response to ws object
     * @param pokemonAbilities A list of com.bankaya.deliverable.pokedex.pokemodel.model.PokemonAbility
     * @return
     */
    private List<Abilities> parseResponse(final List<PokemonAbility> pokemonAbilities) {
        final List<Abilities> abilities = new ArrayList<>(pokemonAbilities.size());
        pokemonAbilities.forEach(a -> {
            Abilities ability = new Abilities();
            ability.setSlot(a.getSlot());
            ability.setIsHidden(a.isHidden());
            ability.setAbility(EndPointUtils.createNamedAPIResource(a.getAbility()));

            abilities.add(ability);
        });

        return abilities;
    }
}


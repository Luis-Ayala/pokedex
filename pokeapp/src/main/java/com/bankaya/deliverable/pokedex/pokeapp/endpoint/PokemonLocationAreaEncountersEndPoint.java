package com.bankaya.deliverable.pokedex.pokeapp.endpoint;

import com.bankaya.deliverable.pokedex.pokeapi.v2.controller.PokemonController;
import com.bankaya.deliverable.pokedex.pokeapp.data.repository.RequestRepository;
import com.bankaya.deliverable.pokedex.pokeapp.util.EndPointUtils;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.ws.GetPokemonLocationAreaEncountersRequest;
import com.bankaya.deliverable.pokedex.ws.GetPokemonLocationAreaEncountersResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletRequest;

@Endpoint
public class PokemonLocationAreaEncountersEndPoint {
    private static final Logger LOG = LoggerFactory.getLogger(PokemonLocationAreaEncountersEndPoint.class);

    private static final String NAMESPACE_URI = "http://bankaya.com/deliverable/pokedex/ws";
    private static final String methodName = "getPokemonLocationAreaEncounters";

    private final PokemonController pokemonController;
    private final RequestRepository requestRepository;
    private final HttpServletRequest httpRequest;

    public PokemonLocationAreaEncountersEndPoint(PokemonController pokemonController, RequestRepository requestRepository,
                                                 HttpServletRequest httpRequest) {
        this.pokemonController = pokemonController;
        this.requestRepository = requestRepository;
        this.httpRequest = httpRequest;
    }

    /**
     * Entry method for getPokemonLocationAreaEncountersRequest
     * @param request GetPokemonLocationAreaEncountersRequest object
     * @return GetPokemonLocationAreaEncountersResponse object
     * @throws PokedexException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonLocationAreaEncountersRequest")
    @ResponsePayload
    public GetPokemonLocationAreaEncountersResponse getPokemonLocationAreaEncounters(
            @RequestPayload GetPokemonLocationAreaEncountersRequest request) throws PokedexException {
        LOG.debug("Starting getPokemonLocationAreaEncounters");
        EndPointUtils.saveRequest(requestRepository, httpRequest.getRemoteAddr(), methodName, request.getPokemon());
        final GetPokemonLocationAreaEncountersResponse response = new GetPokemonLocationAreaEncountersResponse();
        final String location = pokemonController.getLocationAreaEncounters(request.getPokemon());
        response.setLocationAreaEncounters(location);
        LOG.debug("Starting getPokemonLocationAreaEncounters");
        return response;
    }
}


package com.bankaya.deliverable.pokedex.pokeapp.endpoint;

import com.bankaya.deliverable.pokedex.pokeapi.v2.controller.PokemonController;
import com.bankaya.deliverable.pokedex.pokeapp.data.repository.RequestRepository;
import com.bankaya.deliverable.pokedex.pokeapp.util.EndPointUtils;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.ws.GetPokemonNameRequest;
import com.bankaya.deliverable.pokedex.ws.GetPokemonNameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletRequest;

@Endpoint
public class PokemonNameEndPoint {
    private static final Logger LOG = LoggerFactory.getLogger(PokemonNameEndPoint.class);

    private static final String NAMESPACE_URI = "http://bankaya.com/deliverable/pokedex/ws";
    private static final String methodName = "getPokemonName";

    private final PokemonController pokemonController;
    private final RequestRepository requestRepository;
    private final HttpServletRequest httpRequest;

    public PokemonNameEndPoint(PokemonController pokemonController, RequestRepository requestRepository,
                               HttpServletRequest httpRequest) {
        this.pokemonController = pokemonController;
        this.requestRepository = requestRepository;
        this.httpRequest = httpRequest;
    }

    /**
     * Entry method for getPokemonNameRequest
     * @param request GetPokemonNameRequest object
     * @return GetPokemonNameResponse object
     * @throws PokedexException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonNameRequest")
    @ResponsePayload
    public GetPokemonNameResponse getPokemonName(@RequestPayload GetPokemonNameRequest request) throws PokedexException {
        LOG.debug("Starting getPokemonName");
        EndPointUtils.saveRequest(requestRepository, httpRequest.getRemoteAddr(), methodName, request.getPokemon());
        final GetPokemonNameResponse response = new GetPokemonNameResponse();
        response.setName(pokemonController.getPokemonName(request.getPokemon()));
        LOG.debug("Starting getPokemonName");
        return response;
    }
}


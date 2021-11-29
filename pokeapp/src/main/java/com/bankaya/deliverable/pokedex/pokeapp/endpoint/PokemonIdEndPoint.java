package com.bankaya.deliverable.pokedex.pokeapp.endpoint;

import com.bankaya.deliverable.pokedex.pokeapi.v2.controller.PokemonController;
import com.bankaya.deliverable.pokedex.pokeapp.data.repository.RequestRepository;
import com.bankaya.deliverable.pokedex.pokeapp.util.EndPointUtils;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.ws.GetPokemonIdRequest;
import com.bankaya.deliverable.pokedex.ws.GetPokemonIdResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletRequest;

@Endpoint
public class PokemonIdEndPoint {
    private static final Logger LOG = LoggerFactory.getLogger(PokemonIdEndPoint.class);

    private static final String NAMESPACE_URI = "http://bankaya.com/deliverable/pokedex/ws";
    private static final String methodName = "getPokemonId";

    private final PokemonController pokemonController;
    private final RequestRepository requestRepository;
    private final HttpServletRequest httpRequest;

    public PokemonIdEndPoint(PokemonController pokemonController, RequestRepository requestRepository,
                             HttpServletRequest httpRequest) {
        this.pokemonController = pokemonController;
        this.requestRepository = requestRepository;
        this.httpRequest = httpRequest;
    }

    /**
     * Entry method for getPokemonHeldItemsRequest
     * @param request GetPokemonIdRequest object
     * @return GetPokemonIdResponse object
     * @throws PokedexException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonIdRequest")
    @ResponsePayload
    public GetPokemonIdResponse getPokemonId(@RequestPayload GetPokemonIdRequest request) throws PokedexException {
        LOG.debug("Starting PokemonIdEndPoint");
        EndPointUtils.saveRequest(requestRepository, httpRequest.getRemoteAddr(), methodName, request.getPokemon());
        final GetPokemonIdResponse response = new GetPokemonIdResponse();
        final Integer id = pokemonController.getPokemonId(request.getPokemon());
        response.setId(id);
        LOG.debug("Starting PokemonIdEndPoint");
        return response;
    }
}


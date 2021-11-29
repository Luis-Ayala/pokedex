package com.bankaya.deliverable.pokedex.pokeapp.endpoint;

import com.bankaya.deliverable.pokedex.pokeapi.v2.controller.PokemonController;
import com.bankaya.deliverable.pokedex.pokeapp.data.repository.RequestRepository;
import com.bankaya.deliverable.pokedex.pokeapp.util.EndPointUtils;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.ws.GetPokemonBaseExperienceRequest;
import com.bankaya.deliverable.pokedex.ws.GetPokemonBaseExperienceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServletRequest;

@Endpoint
public class PokemonBaseExperienceEndPoint {
    private static final Logger LOG = LoggerFactory.getLogger(PokemonBaseExperienceEndPoint.class);

    private static final String NAMESPACE_URI = "http://bankaya.com/deliverable/pokedex/ws";
    private static final String methodName = "getPokemonBaseExperience";

    private final PokemonController pokemonController;
    private final RequestRepository requestRepository;
    private final HttpServletRequest httpRequest;

    public PokemonBaseExperienceEndPoint(PokemonController pokemonController, RequestRepository requestRepository,
                                         HttpServletRequest httpRequest) {
        this.pokemonController = pokemonController;
        this.requestRepository = requestRepository;
        this.httpRequest = httpRequest;
    }

    /**
     * Entry method for getPokemonBaseExperienceRequest
     * @param request GetPokemonBaseExperienceRequest object
     * @return GetPokemonBaseExperienceResponse object
     * @throws PokedexException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonBaseExperienceRequest")
    @ResponsePayload
    public GetPokemonBaseExperienceResponse getPokemonBaseExperience(@RequestPayload GetPokemonBaseExperienceRequest request) throws PokedexException {
        LOG.debug("Starting getPokemonBaseExperience");
        EndPointUtils.saveRequest(requestRepository, httpRequest.getRemoteAddr(), methodName, request.getPokemon());
        GetPokemonBaseExperienceResponse response = new GetPokemonBaseExperienceResponse();
        final Integer experience = pokemonController.getPokemonBaseExperience(request.getPokemon());
        response.setBaseExperience(experience);
        LOG.debug("Starting getPokemonBaseExperience");
        return response;
    }
}


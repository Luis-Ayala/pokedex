package com.bankaya.deliverable.pokedex.pokeapp.endpoint;

import com.bankaya.deliverable.pokedex.pokeapi.v2.controller.PokemonController;
import com.bankaya.deliverable.pokedex.pokeapp.data.repository.RequestRepository;
import com.bankaya.deliverable.pokedex.pokeapp.util.EndPointUtils;
import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import com.bankaya.deliverable.pokedex.pokemodel.model.PokemonHeldItem;
import com.bankaya.deliverable.pokedex.ws.GetPokemonHeldItemsRequest;
import com.bankaya.deliverable.pokedex.ws.GetPokemonHeldItemsResponse;
import com.bankaya.deliverable.pokedex.ws.Items;
import com.bankaya.deliverable.pokedex.ws.VersionDetails;
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
public class PokemonHeldItemEndPoint {
    private static final Logger LOG = LoggerFactory.getLogger(PokemonHeldItemEndPoint.class);

    private static final String NAMESPACE_URI = "http://bankaya.com/deliverable/pokedex/ws";
    private static final String methodName = "getPokemonHeldItems";

    private final PokemonController pokemonController;
    private final RequestRepository requestRepository;
    private final HttpServletRequest httpRequest;

    public PokemonHeldItemEndPoint(PokemonController pokemonController, RequestRepository requestRepository,
                                   HttpServletRequest httpRequest) {
        this.pokemonController = pokemonController;
        this.requestRepository = requestRepository;
        this.httpRequest = httpRequest;
    }

    /**
     * Entry method for getPokemonHeldItemsRequest
     * @param request GetPokemonHeldItemsRequest object
     * @return GetPokemonHeldItemsResponse object
     * @throws PokedexException
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonHeldItemsRequest")
    @ResponsePayload
    public GetPokemonHeldItemsResponse getPokemonHeldItems(@RequestPayload GetPokemonHeldItemsRequest request) throws PokedexException {
        LOG.debug("Starting getPokemonHeldItems");
        EndPointUtils.saveRequest(requestRepository, httpRequest.getRemoteAddr(), methodName, request.getPokemon());
        final GetPokemonHeldItemsResponse response = new GetPokemonHeldItemsResponse();
        final List<PokemonHeldItem> heldItems = pokemonController.getHeldItems(request.getPokemon());
        response.getHeldItems().addAll(parseResponse(heldItems));
        LOG.debug("Starting getPokemonHeldItems");
        return response;
    }

    private List<Items> parseResponse (final List<PokemonHeldItem> heldItems) {
        List<Items> items = new ArrayList<>(heldItems.size());

        heldItems.forEach(i -> {
            final Items entry = new Items();
            entry.setItem(EndPointUtils.createNamedAPIResource(i.getItem()));

            i.getVersionDetails().forEach(v -> {
                final VersionDetails versionDetails = new VersionDetails();
                versionDetails.setRarity(v.getRarity());
                versionDetails.setVersion(EndPointUtils.createNamedAPIResource(v.getVersion()));
                entry.getVersionDetails().add(versionDetails);
            });

            items.add(entry);
        });

        return items;
    }
}

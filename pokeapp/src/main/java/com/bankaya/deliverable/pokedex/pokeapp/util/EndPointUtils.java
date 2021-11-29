package com.bankaya.deliverable.pokedex.pokeapp.util;

import com.bankaya.deliverable.pokedex.pokeapp.data.entity.RequestEntity;
import com.bankaya.deliverable.pokedex.pokeapp.data.repository.RequestRepository;
import com.bankaya.deliverable.pokedex.ws.NamedAPIResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class EndPointUtils {
    private static final Logger LOG = LoggerFactory.getLogger(EndPointUtils.class);

    /**
     * Map a NamedAPIResource object with PokeApp object
     * @param apiResource A com.bankaya.deliverable.pokedex.pokemodel.model.NamedAPIResource object
     * @return A com.bankaya.deliverable.pokedex.ws.NamedAPIResource object
     */
    public static NamedAPIResource createNamedAPIResource(
            com.bankaya.deliverable.pokedex.pokemodel.model.NamedAPIResource apiResource) {
        final NamedAPIResource namedAPIResource = new NamedAPIResource();
        namedAPIResource.setName(apiResource.getName());
        namedAPIResource.setUrl(apiResource.getUrl());

        return namedAPIResource;
    }

    /**
     * Save the audit object into h2 database
     * @param requestRepository A request repository object
     * @param ip The remote ip
     * @param methodName´The method name
     * @param pokemon the Pokemon name
     */
    public static void saveRequest(final RequestRepository requestRepository, final String ip, final String methodName,
                            final String pokemon) {
        try {
            requestRepository.save(new RequestEntity(ip, LocalDateTime.now(), methodName, pokemon));
        } catch (Exception e) {
            LOG.error("Error in EndPointUtils.saveRequest.", e);
        }
    }
}


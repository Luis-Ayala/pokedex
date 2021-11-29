package com.bankaya.deliverable.pokedex.pokeapi.v2.util;

import com.bankaya.deliverable.pokedex.pokemodel.exception.PokedexException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ExceptionUtil {

    public static PokedexException of(Exception e) {
        PokedexException pokedexException = new PokedexException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage());

        if(e instanceof HttpClientErrorException) {
            HttpClientErrorException ex = (HttpClientErrorException) e;

            if(ex.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                pokedexException = new PokedexException(ex.getStatusCode().getReasonPhrase(),
                        ex.getStatusCode().value(),
                        "Pokemon not found...yet.");
            } else {
                pokedexException = new PokedexException(ex.getStatusCode().getReasonPhrase(),
                        ex.getStatusCode().value(),
                        ex.getMessage());
                }
        }

        return pokedexException;
    }
}


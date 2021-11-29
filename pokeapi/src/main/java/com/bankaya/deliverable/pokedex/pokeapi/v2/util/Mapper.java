package com.bankaya.deliverable.pokedex.pokeapi.v2.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

    /**
     * Auxiliary method to map a JsonNode object with the given class type. Will Always return an array.
     * @param json A JsonNode object
     * @param object The target class
     * @param <T> The type of the target class
     * @return A wrapper object with the given class type
     * @throws JsonProcessingException
     */
    public static <T> Wrapper<T[]> map(final JsonNode json, final T[] object) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final T data = (T) objectMapper.readValue(json.toString(), object.getClass());
        return new Wrapper<T[]>((T[]) data);
    }
}


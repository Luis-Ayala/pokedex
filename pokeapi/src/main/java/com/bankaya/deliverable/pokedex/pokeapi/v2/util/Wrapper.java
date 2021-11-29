package com.bankaya.deliverable.pokedex.pokeapi.v2.util;

public class Wrapper<T> {

    private final T data;

    public T getData() {
        return data;
    }

    public Wrapper(T data) {
        this.data = data;
    }
}


package com.bankaya.deliverable.pokedex.pokemodel.exception;

public class PokedexException extends Exception {
    private final String httpStatus;
    private final int httpCode;
    private final String message;


    public PokedexException(final String httpStatus, final int httpCode, final String message) {
        this.httpStatus = httpStatus;
        this.httpCode = httpCode;
        this.message = message;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public int getHttpCode() {
        return httpCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}


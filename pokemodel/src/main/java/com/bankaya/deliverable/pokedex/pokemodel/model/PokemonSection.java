package com.bankaya.deliverable.pokedex.pokemodel.model;

public enum PokemonSection {

    ABILITIES("abilities"),
    BASE_EXPERIENCE("base_experience"),
    ID("id"),
    NAME("name"),
    HELD_ITEMS("held_items"),
    LOCATION_AREA_ENCOUNTERS("location_area_encounters");

    private final String text;

    PokemonSection(String text) {
        this.text = text;
    }

    public String getName() {
        return text;
    }
}


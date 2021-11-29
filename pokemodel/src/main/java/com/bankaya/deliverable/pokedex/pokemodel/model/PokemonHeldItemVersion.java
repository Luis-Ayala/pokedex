package com.bankaya.deliverable.pokedex.pokemodel.model;

public class PokemonHeldItemVersion {
    private Integer rarity;
    private NamedAPIResource version;

    public Integer getRarity() {
        return rarity;
    }

    public void setRarity(Integer rarity) {
        this.rarity = rarity;
    }

    public NamedAPIResource getVersion() {
        return version;
    }

    public void setVersion(NamedAPIResource version) {
        this.version = version;
    }
}


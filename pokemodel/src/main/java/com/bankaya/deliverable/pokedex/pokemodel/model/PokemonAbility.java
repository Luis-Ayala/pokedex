package com.bankaya.deliverable.pokedex.pokemodel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonAbility {
    @JsonProperty("is_hidden")
    private boolean isHidden;

    @JsonProperty("slot")
    private Integer slot;

    @JsonProperty("ability")
    private NamedAPIResource namedAPIResource;

    public PokemonAbility() { }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        this.isHidden = hidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public NamedAPIResource getAbility() {
        return namedAPIResource;
    }

    public void setAbility(NamedAPIResource namedAPIResource) {
        this.namedAPIResource = namedAPIResource;
    }
}


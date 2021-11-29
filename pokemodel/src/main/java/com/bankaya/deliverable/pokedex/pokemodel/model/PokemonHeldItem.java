package com.bankaya.deliverable.pokedex.pokemodel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PokemonHeldItem {

    @JsonProperty("item")
    private NamedAPIResource item;

    @JsonProperty("version_details")
    private List<PokemonHeldItemVersion> versionDetails;

    public NamedAPIResource getItem() {
        return item;
    }

    public void setItem(NamedAPIResource item) {
        this.item = item;
    }

    public List<PokemonHeldItemVersion> getVersionDetails() {
        return versionDetails;
    }

    public void setVersionDetails(List<PokemonHeldItemVersion> versionDetails) {
        this.versionDetails = versionDetails;
    }
}


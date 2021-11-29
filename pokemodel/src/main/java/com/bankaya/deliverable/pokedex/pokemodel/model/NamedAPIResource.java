package com.bankaya.deliverable.pokedex.pokemodel.model;

public class NamedAPIResource {
    private String name;
    private String url;

    public NamedAPIResource() {}

    /**
     * The name of the referenced resource.
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * The URL of the referenced resource.
     * @return
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


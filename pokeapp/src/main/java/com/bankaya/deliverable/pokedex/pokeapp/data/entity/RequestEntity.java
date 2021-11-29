package com.bankaya.deliverable.pokedex.pokeapp.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Principal entity for PokeApp project, contains useful information for audit process
 */
@Entity
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ip;

    private LocalDateTime dateTime;

    private String method;

    private String pokemon;

    public RequestEntity() {
    }

    public RequestEntity(String ip, LocalDateTime dateTime, String method, String pokemon) {
        this.ip = ip;
        this.dateTime = dateTime;
        this.method = method;
        this.pokemon = pokemon;
    }
}


package com.bankaya.deliverable.pokedex.pokeapp.data.repository;

import com.bankaya.deliverable.pokedex.pokeapp.data.entity.RequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository class for PokeApp project
 */
@Repository
public interface RequestRepository extends CrudRepository<RequestEntity, Long> {
}


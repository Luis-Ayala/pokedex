package com.bankaya.deliverable.pokedex.pokeapp.endpoint;

import com.bankaya.deliverable.pokedex.ws.*;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokeappIntegrationTest {

    private final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    private static final String POKEMON = "ditto";
    private WebServiceTemplate ws;

    @LocalServerPort
    private int port = 0;

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetPokemonNameRequest.class));
        marshaller.afterPropertiesSet();
        ws = new WebServiceTemplate(marshaller);
    }

    @Test
    public void getPokemonAbilitiesTest() {
        ws = new WebServiceTemplate(marshaller);
        GetPokemonAbilitiesRequest request = new GetPokemonAbilitiesRequest();
        request.setPokemon(POKEMON);

        final var response = ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response instanceof GetPokemonAbilitiesResponse).isTrue();
        Assertions.assertThat(((GetPokemonAbilitiesResponse) response).getAbilities()).isNotNull();
        Assertions.assertThat(((GetPokemonAbilitiesResponse) response).getAbilities().size() > 0).isTrue();
    }

    @Test
    public void getPokemonBaseExperienceTest() {
        ws = new WebServiceTemplate(marshaller);
        GetPokemonBaseExperienceRequest request = new GetPokemonBaseExperienceRequest();
        request.setPokemon(POKEMON);

        final var response = ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response instanceof GetPokemonBaseExperienceResponse).isTrue();
        Assertions.assertThat(((GetPokemonBaseExperienceResponse) response).getBaseExperience()).isNotNull();
    }

    @Test
    public void getPokemonHeldItemsTest() {
        ws = new WebServiceTemplate(marshaller);
        GetPokemonHeldItemsRequest request = new GetPokemonHeldItemsRequest();
        request.setPokemon(POKEMON);

        final var response = ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response instanceof GetPokemonHeldItemsResponse).isTrue();
        Assertions.assertThat(((GetPokemonHeldItemsResponse) response).getHeldItems()).isNotNull();
        Assertions.assertThat(((GetPokemonHeldItemsResponse) response).getHeldItems().size() > 0).isTrue();
    }

    @Test
    public void getPokemonIdTest() {
        ws = new WebServiceTemplate(marshaller);
        GetPokemonIdRequest request = new GetPokemonIdRequest();
        request.setPokemon(POKEMON);

        final var response = ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response instanceof GetPokemonIdResponse).isTrue();
        Assertions.assertThat(((GetPokemonIdResponse) response).getId()).isNotNull();
    }

    @Test
    public void getPokemonLocationAreaEncountersTest() {
        ws = new WebServiceTemplate(marshaller);
        GetPokemonLocationAreaEncountersRequest request = new GetPokemonLocationAreaEncountersRequest();
        request.setPokemon(POKEMON);

        final var response = ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response instanceof GetPokemonLocationAreaEncountersResponse).isTrue();
        Assertions.assertThat(((GetPokemonLocationAreaEncountersResponse) response).getLocationAreaEncounters()).isNotNull();
    }

    @Test
    public void getPokemonNameRequestTest() {
        ws = new WebServiceTemplate(marshaller);
        GetPokemonNameRequest request = new GetPokemonNameRequest();
        request.setPokemon(POKEMON);

        final var response = ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request);

        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response instanceof GetPokemonNameResponse).isTrue();
        Assertions.assertThat(((GetPokemonNameResponse) response).getName()).isEqualTo(POKEMON);
    }
}


package revision.pokemon.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import revision.pokemon.model.Pokemon;

@Service
public class PokeService {

    @Value("${revision.pokemon.api.url}")
    private String pokeAPI;


    public Optional<Pokemon> createPokemon(String pokemon){
        Optional<String> pokeJSONStr = getPokeJSONStr(pokemon);
        if(pokeJSONStr.isPresent()){
            Pokemon newPoke = createPokeObj(getPokeJSON(pokeJSONStr.get()));
            System.out.println("Returned Poke");
            return Optional.of(newPoke);
        } else {
            return Optional.empty();
        }
    }

    private static Pokemon createPokeObj(JsonObject pokeJson){
        Pokemon pokemon = new Pokemon();
        pokemon.setWeight(pokeJson.getJsonNumber("weight").doubleValue());
        pokemon.setHeight(pokeJson.getJsonNumber("height").doubleValue());
        pokemon.setName(pokeJson.getString("name"));
        pokemon.setPokeNumber(pokeJson.getInt("order"));
        return pokemon;
    }

    private static JsonObject getPokeJSON(String jsonString){
        // System.out.println(jsonString);
        // InputStream is = new ByteArrayInputStream(jsonString.getBytes());
        // Reader isr = new InputStreamReader(is);
        // JsonReader jsr = Json.createReader(isr);
        // return jsr.readObject();

        StringReader sr = new StringReader(jsonString.toString());
        JsonReader jsr = Json.createReader(sr);
        return (JsonObject)jsr.read();
        }

   
    private Optional<String> getPokeJSONStr(String pokemon){
        String pokeURL = UriComponentsBuilder.fromUriString(pokeAPI).pathSegment(pokemon.toLowerCase()).toUriString();
        System.out.println(pokeURL);
        RestTemplate restTemplate= new RestTemplate();
        RequestEntity<Void> req = RequestEntity.get(pokeURL).build();
        try{
            ResponseEntity<String> resp  = restTemplate.exchange(req,String.class);
            // System.out.println(resp.getBody());
            return Optional.of(resp.getBody());
        }
        catch(HttpClientErrorException e){
            return Optional.empty();
        }
    }



    public String getPokeAPI() {
        return pokeAPI;
    }
    public void setPokeAPI(String pokeAPI) {
        this.pokeAPI = pokeAPI;
    }

}

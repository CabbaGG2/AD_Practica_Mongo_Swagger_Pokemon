package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.model.Entrenador;
import org.example.model.Pokemon;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(RestPokemon.MAPPING)
public class RestPokemon {

    public static final String MAPPING = "/mongodb/pokemon";

    @Autowired
    private PokemonService pokemonService;

    @PostMapping("/guardar")
    public ResponseEntity<Pokemon> guardar(Pokemon pokemon){
        pokemonService.crearPokemon(pokemon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Pokemon>> listarColeccion(){
        List<Pokemon> pokemons = pokemonService.buscarPokemones();
        return new ResponseEntity<>(pokemons,HttpStatus.OK);
    }

    @GetMapping("/getEntrenadorDePokemon/{id}")
    public ResponseEntity<Entrenador> actualizarGrupo(@PathVariable String id) {
        Entrenador e = pokemonService.buscarEntrenadorDePokemon(id);

        if (e == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Pokemon> actualizar(@RequestBody Pokemon pokemon){
        pokemonService.actualizarPokemon(pokemon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Pokemon> eliminar(@PathVariable String id) {
        pokemonService.eliminarPokemon(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @JsonSerialize
    @PostMapping("/serializar/{id}")
    public ResponseEntity<Pokemon> serializar(@PathVariable String id) throws IOException {
        Pokemon pokemon = pokemonService.buscarPokemon(id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("pokemon.json"),pokemon);
        return ResponseEntity.ok(pokemon);
    }

    @JsonDeserialize
    @GetMapping("/deserializar")
    public ResponseEntity<Pokemon> deserializar() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Pokemon pokemon = mapper.readValue(new File("pokemon.json"), Pokemon.class);
        pokemonService.actualizarPokemon(pokemon);
        return ResponseEntity.ok(pokemon);
    }

}

package org.example.controller;

import org.example.model.Entrenador;
import org.example.model.Pokemon;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}

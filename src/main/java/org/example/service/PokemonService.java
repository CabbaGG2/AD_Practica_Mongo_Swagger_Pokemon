package org.example.service;

import org.example.model.Entrenador;
import org.example.model.Pokemon;
import org.example.repository.EntrenadorRepository;
import org.example.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepo;
    private final EntrenadorRepository entrenadorRepo;

    public PokemonService(PokemonRepository pokemonRepo, EntrenadorRepository entrenadorRepo) {
        this.pokemonRepo = pokemonRepo;
        this.entrenadorRepo = entrenadorRepo;
    }

    public void crearPokemon(Pokemon p){
        pokemonRepo.save(p);
    }

    public Pokemon buscarPokemon(String id){
        return pokemonRepo.findById(id).orElse(null);
    }

    //buscar todos los pokemones
    public List<Pokemon> buscarPokemones(){
        return pokemonRepo.findAll();
    }

    //Buscar entrenador de un pokemon
    public Entrenador buscarEntrenadorDePokemon(String idPokemon) {
        Pokemon pokemon = buscarPokemon(idPokemon);
        if (pokemon == null) return null;

        Entrenador entrenador = entrenadorRepo.findById(pokemon.getEntrenadorId()).orElse(null);

        return entrenador;
    }

    public void eliminarPokemon(String id){
        pokemonRepo.deleteById(id);
    }

    /*public void añadirEntrenadorAPokemon(String idPokemon, String nombreEntrenador) {
        Pokemon pokemon = buscarPokemon(idPokemon);
        List<Entrenador> entrenador = entrenadorRepo.findByName(nombreEntrenador);
        if (pokemon != null && entrenador != null) {
            pokemon.setEntrenadorId(entrenador.toString());
            pokemonRepo.save(pokemon);
        }
    }*/

}

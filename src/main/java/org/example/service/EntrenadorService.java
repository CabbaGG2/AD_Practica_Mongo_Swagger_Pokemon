package org.example.service;

import org.example.model.Entrenador;
import org.example.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepo;

    public EntrenadorService(EntrenadorRepository entrenadorRepo) {
        this.entrenadorRepo = entrenadorRepo;
    }

    public void crearEntrenador(Entrenador e){
        entrenadorRepo.save(e);
    }

    public Entrenador buscarEntrenador(String id){
        return entrenadorRepo.findById(id).orElse(null);
    }

    //buscamos todos los entrenadores

    public List<Entrenador> buscarEntrenadores(){
        return entrenadorRepo.findAll();
    }

    public void eliminarEntrenador(String id){
        entrenadorRepo.deleteById(id);
    }

    public void actualizarEntrenador(Entrenador e){
        entrenadorRepo.save(e);
    }
}

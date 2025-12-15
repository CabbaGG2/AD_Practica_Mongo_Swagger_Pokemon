package org.example.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import org.example.model.Entrenador;
import org.example.repository.EntrenadorRepository;
import org.example.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(RestEntrenador.MAPPING)
public class RestEntrenador {

    public static final String MAPPING = "/mongodb/entrenador";

    @Autowired
    private EntrenadorService entrenadorService;

    @PostMapping("/guardar")
    public ResponseEntity<Entrenador> guardar(@RequestBody Entrenador entrenador){
        entrenadorService.crearEntrenador(entrenador);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<java.util.List<Entrenador>> listarColeccion(){
        List<Entrenador> entrenadores = entrenadorService.buscarEntrenadores();
        return new ResponseEntity<>(entrenadores,HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Entrenador> eliminar(@PathVariable String id) {
        entrenadorService.eliminarEntrenador(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<Entrenador> actualizar(@RequestBody Entrenador entrenador){
        entrenadorService.actualizarEntrenador(entrenador);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/Serializar/{id}")
    public ResponseEntity<Entrenador> serializar(@PathVariable String id) throws IOException {

        Entrenador entrenador = entrenadorService.buscarEntrenador(id);
        return ResponseEntity.ok(entrenador);
    }

}

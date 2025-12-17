package org.example.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.model.Entrenador;
import org.example.repository.EntrenadorRepository;
import org.example.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(RestEntrenador.MAPPING)
@Tag(name = "Entrenador", description = "API REST para la gestión de entrenadores")
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

    @JsonSerialize
    @PostMapping("/serializar/{id}")
    public ResponseEntity<Entrenador> serializar(@PathVariable String id) throws IOException {

        Entrenador entrenador = entrenadorService.buscarEntrenador(id);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("entrenador.json"),entrenador);
        return ResponseEntity.ok(entrenador);
    }

    @JsonDeserialize
    @GetMapping("/deserializar")
    public ResponseEntity<Entrenador> deserializar() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Entrenador entrenador = mapper.readValue(new File("entrenador.json"), Entrenador.class);
        entrenadorService.actualizarEntrenador(entrenador);
        return ResponseEntity.ok(entrenador);
    }

}

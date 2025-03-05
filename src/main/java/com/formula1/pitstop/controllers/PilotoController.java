package com.formula1.pitstop.controllers;

import com.formula1.pitstop.entities.Piloto;
import com.formula1.pitstop.repositories.PilotoRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pilotos")
public class PilotoController {
    private PilotoRepository pilotoRepository;

    public PilotoController(PilotoRepository pilotoRepository){
        this.pilotoRepository = pilotoRepository;
    }

    //Obtener todos los pilotos
    @GetMapping("/consultaGeneral")
    public List<Piloto> getAllPilotos(){
        return pilotoRepository.findAll();
    }

    //Obtener un piloto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Piloto> getPilotoById(@PathVariable Long id){
        Optional<Piloto> piloto = pilotoRepository.findById(id);
        return piloto.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    //Crear piloto
    @PostMapping("/crear")
    public Piloto creartePiloto(@RequestBody Piloto piloto){
        return pilotoRepository.save(piloto);
    }

    //Actualizar un piloto ecistente
    @PutMapping("/{id}")
    public ResponseEntity<Piloto> updatePiloto(@PathVariable Long id, @RequestBody Piloto pilotoDetails){
        return pilotoRepository.findById(id).map(piloto -> {
            piloto.setNombre(pilotoDetails.getNombre());
            piloto.setNacionalidad(pilotoDetails.getNacionalidad());
            piloto.setEdad(pilotoDetails.getEdad());
            pilotoRepository.save(piloto);
            return ResponseEntity.ok(piloto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un piloto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiloto(@PathVariable Long id) {
        if (pilotoRepository.existsById(id)) {
            pilotoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

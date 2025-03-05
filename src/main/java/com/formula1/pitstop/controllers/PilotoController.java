package com.formula1.pitstop.controllers;
import com.formula1.pitstop.entities.Piloto;
import com.formula1.pitstop.services.PilotoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/pilotos")
public class PilotoController {
    private PilotoService pilotoService;

    @Autowired
    public PilotoController(PilotoService pilotoService){
        this.pilotoService = pilotoService;
    }

    //Obtener todos los pilotos
    @GetMapping("/consultaGeneral")
    public List<Piloto> getAllPilotos(){
        return pilotoService.getAllPilotos();
    }

    //Obtener un piloto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Piloto> getPilotoById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(pilotoService.getPilotoById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Crear piloto
    @PostMapping("/crear")
    public Piloto creartePiloto(@RequestBody Piloto piloto){
        return pilotoService.creartePiloto(piloto);
    }

    //Actualizar un piloto ecistente
    @PutMapping("/{id}")
    public ResponseEntity<Piloto> updatePiloto(@PathVariable Long id, @RequestBody Piloto pilotoDetails){
        try {
            return ResponseEntity.ok(pilotoService.updatePiloto(id,pilotoDetails));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un piloto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiloto(@PathVariable Long id) {
        try {
            pilotoService.deletePiloto(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}

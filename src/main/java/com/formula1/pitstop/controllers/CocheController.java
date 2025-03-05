package com.formula1.pitstop.controllers;
import com.formula1.pitstop.entities.Coche;
import com.formula1.pitstop.repositories.CocheRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/coches")
public class CocheController {
    private final CocheRepository cocheRepository;

    public CocheController(CocheRepository cocheRepository) {
        this.cocheRepository = cocheRepository;
    }

    @GetMapping(("/consultaGeneral"))
    public List<Coche> getAllCoches() {
        return cocheRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coche> getCocheById(@PathVariable Long id) {
        Optional<Coche> coche = cocheRepository.findById(id);
        return coche.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public Coche createCoche(@RequestBody Coche coche) {
        return cocheRepository.save(coche);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coche> updateCoche(@PathVariable Long id, @RequestBody Coche cocheDetails) {
        return cocheRepository.findById(id).map(coche -> {
            coche.setModelo(cocheDetails.getModelo());
            coche.setMotor(cocheDetails.getMotor());
            coche.setEscuderia(cocheDetails.getEscuderia());
            cocheRepository.save(coche);
            return ResponseEntity.ok(coche);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoche(@PathVariable Long id) {
        if (cocheRepository.existsById(id)) {
            cocheRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
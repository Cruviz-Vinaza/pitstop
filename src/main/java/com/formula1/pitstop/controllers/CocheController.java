package com.formula1.pitstop.controllers;
import com.formula1.pitstop.entities.Coche;
import com.formula1.pitstop.services.CocheService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/coches")
public class CocheController {
    private final CocheService cocheService;

    @Autowired
    public CocheController(CocheService cocheService) {
        this.cocheService = cocheService;
    }

    @GetMapping(("/consultaGeneral"))
    public List<Coche> getAllCoches() {
        return cocheService.getAllCoches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coche> getCocheById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cocheService.getCocheById(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public Coche createCoche(@RequestBody Coche coche) {
        return cocheService.createCoche(coche);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coche> updateCoche(@PathVariable Long id, @RequestBody Coche cocheDetails) {
        try {
            return ResponseEntity.ok(cocheService.updateCoche(id,cocheDetails));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoche(@PathVariable Long id) {
        try{
            cocheService.deleteCoche(id);
            return  ResponseEntity.noContent().build();
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
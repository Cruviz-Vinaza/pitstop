package com.formula1.pitstop.controllers;

import com.formula1.pitstop.entities.Coche;
import com.formula1.pitstop.entities.Equipo;
import com.formula1.pitstop.entities.Piloto;
import com.formula1.pitstop.repositories.CocheRepository;
import com.formula1.pitstop.repositories.EquipoRepository;
import com.formula1.pitstop.repositories.PilotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoRepository equipoRepository;
    private final PilotoRepository pilotoRepository;
    private final CocheRepository cocheRepository;

    @Autowired
    public EquipoController(EquipoRepository equipoRepository, PilotoRepository pilotoRepository, CocheRepository cocheRepository) {
        this.equipoRepository = equipoRepository;
        this.pilotoRepository = pilotoRepository;
        this.cocheRepository = cocheRepository;
    }

    @GetMapping("/consultaGeneral")
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        Optional<Equipo> equipo = equipoRepository.findById(id);
        return equipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearEquipo(@RequestBody Equipo equipo) {
        Optional<Piloto> pilotoOpt = pilotoRepository.findById(equipo.getPiloto().getId());
        Optional<Coche> cocheOpt = cocheRepository.findById(equipo.getCoche().getId());

        if (pilotoOpt.isPresent() && cocheOpt.isPresent()) {
            equipo.setPiloto(pilotoOpt.get());
            equipo.setCoche(cocheOpt.get());

            Equipo nuevoEquipo = equipoRepository.save(equipo);
            return ResponseEntity.ok(nuevoEquipo);
        } else {
            return ResponseEntity.badRequest().body("Piloto o coche no encontrados");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipoDetails) {
        return equipoRepository.findById(id).map(equipo -> {
            equipo.setNombre(equipoDetails.getNombre());
            equipoRepository.save(equipo);
            return ResponseEntity.ok(equipo);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Long id) {
        if (equipoRepository.existsById(id)) {
            equipoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
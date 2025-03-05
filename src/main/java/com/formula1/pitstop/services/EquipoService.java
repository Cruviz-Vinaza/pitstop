package com.formula1.pitstop.services;

import com.formula1.pitstop.entities.Coche;
import com.formula1.pitstop.entities.Equipo;
import com.formula1.pitstop.entities.Piloto;
import com.formula1.pitstop.repositories.CocheRepository;
import com.formula1.pitstop.repositories.EquipoRepository;
import com.formula1.pitstop.repositories.PilotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final PilotoRepository pilotoRepository;
    private final CocheRepository cocheRepository;

    // Constructor para inyectar las dependencias
    public EquipoService(EquipoRepository equipoRepository, PilotoRepository pilotoRepository, CocheRepository cocheRepository) {
        this.equipoRepository = equipoRepository;
        this.pilotoRepository = pilotoRepository;
        this.cocheRepository = cocheRepository;
    }

    // Obtener todos los equipos
    public List<Equipo> getAllEquipos() {
        return equipoRepository.findAll();
    }

    // Obtener un equipo por su ID
    public Equipo getEquipoById(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));
    }

    // Crear un nuevo equipo
    public Equipo crearEquipo(Equipo equipo) {
        Optional<Piloto> pilotoOpt = pilotoRepository.findById(equipo.getPiloto().getId());
        Optional<Coche> cocheOpt = cocheRepository.findById(equipo.getCoche().getId());

        if (!pilotoOpt.isPresent() || !cocheOpt.isPresent()) {
            throw new EntityNotFoundException("Piloto o Coche no encontrados");
        }

        equipo.setPiloto(pilotoOpt.get());
        equipo.setCoche(cocheOpt.get());
        return equipoRepository.save(equipo);
    }

    // Actualizar un equipo
    public Equipo updateEquipo(Long id, Equipo equipoDetails) {
        return equipoRepository.findById(id)
                .map(equipo -> {
                    equipo.setNombre(equipoDetails.getNombre());
                    return equipoRepository.save(equipo);
                })
                .orElseThrow(() -> new EntityNotFoundException("Equipo no encontrado"));
    }

    // Eliminar un equipo
    public void deleteEquipo(Long id) {
        if (!equipoRepository.existsById(id)) {
            throw new EntityNotFoundException("Equipo no encontrado");
        }
        equipoRepository.deleteById(id);
    }
}


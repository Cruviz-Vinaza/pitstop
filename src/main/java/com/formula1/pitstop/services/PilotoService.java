package com.formula1.pitstop.services;
import com.formula1.pitstop.entities.Piloto;
import com.formula1.pitstop.repositories.PilotoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PilotoService {
    private PilotoRepository pilotoRepository;

    @Autowired
    public PilotoService(PilotoRepository pilotoRepository){
        this.pilotoRepository = pilotoRepository;
    }

    //Obtener todos los pilotos
    public List<Piloto> getAllPilotos(){
        return pilotoRepository.findAll();
    }

    // Obtener un piloto por su ID
    public Piloto getPilotoById(Long id) {
        return pilotoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Piloto no encontrado"));
    }

    //Crear piloto
    public Piloto creartePiloto(Piloto piloto){
        return pilotoRepository.save(piloto);
    }

    //Actualizar un piloto ecistente
    public Piloto updatePiloto(Long id, Piloto pilotoDetails) {
        return pilotoRepository.findById(id)
                .map(piloto -> {
                    piloto.setNombre(pilotoDetails.getNombre());
                    piloto.setNacionalidad(pilotoDetails.getNacionalidad());
                    piloto.setEdad(pilotoDetails.getEdad());
                    return pilotoRepository.save(piloto);
                })
                .orElseThrow(() -> new EntityNotFoundException("Piloto no encontrado"));
    }

    // Eliminar un piloto
    public void deletePiloto(Long id) {
        if (!pilotoRepository.existsById(id)) {
            throw new EntityNotFoundException("Piloto no encontrado");
        }
        pilotoRepository.deleteById(id);
    }

}

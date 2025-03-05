package com.formula1.pitstop.services;
import com.formula1.pitstop.entities.Coche;
import com.formula1.pitstop.repositories.CocheRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CocheService {
    private CocheRepository cocheRepository;

    @Autowired
    public CocheService(CocheRepository cocheRepository){
        this.cocheRepository = cocheRepository;
    }

    public List<Coche> getAllCoches() {
        return cocheRepository.findAll();
    }

    public Coche getCocheById(Long id) {
        return cocheRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coche no encontrado"));
    }

    public Coche createCoche(Coche coche) {
        return cocheRepository.save(coche);
    }

    public Coche updateCoche(Long id,Coche cocheDetails) {
        return cocheRepository.findById(id)
                .map(coche -> {
                    coche.setModelo(cocheDetails.getModelo());
                    coche.setMotor(cocheDetails.getMotor());
                    coche.setEscuderia(cocheDetails.getEscuderia());
                    return cocheRepository.save(coche);
        }).orElseThrow(() -> new EntityNotFoundException("Coche no encontrado"));
    }

    public void deleteCoche(Long id) {
        if (!cocheRepository.existsById(id)) {
            throw new EntityNotFoundException("Coche no encontrado");
        }
        cocheRepository.deleteById(id);
    }
}

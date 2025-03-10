package com.formula1.pitstop.repositories;

import com.formula1.pitstop.entities.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepository extends JpaRepository <Coche, Long> {
}

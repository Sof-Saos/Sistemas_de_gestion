package com.gestion.habitaciones.repositories;
import com.gestion.habitaciones.dto.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    @Query("SELECT h FROM Habitacion h WHERE h.tipo = :tipo")
    List<Habitacion> getHabitacionesByType(@Param("tipo") String tipo);


}

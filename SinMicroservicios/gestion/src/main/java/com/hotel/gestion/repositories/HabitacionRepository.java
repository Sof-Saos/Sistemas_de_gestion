package com.hotel.gestion.repositories;
import com.hotel.gestion.dto.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    @Query("SELECT h FROM Habitacion h WHERE h.tipo = :tipo")
    List<Habitacion> getHabitacionesByType(@Param("tipo") String tipo);

    @Query("SELECT h FROM Habitacion h WHERE h.numero NOT IN(SELECT r.habitacion FROM Reserva r WHERE r.habitacion IS NOT NULL)")
    List<Habitacion> getHabitacionesByFecha(@Param("fecha") Date fecha);


}

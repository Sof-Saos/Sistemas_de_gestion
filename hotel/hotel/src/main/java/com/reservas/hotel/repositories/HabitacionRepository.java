package com.reservas.hotel.repositories;
import com.reservas.hotel.dto.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByTipo(String tipo);
}
package com.gestion.habitaciones.repositories;
import com.gestion.habitaciones.dto.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
}
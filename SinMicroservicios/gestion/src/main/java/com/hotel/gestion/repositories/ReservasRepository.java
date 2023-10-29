package com.hotel.gestion.repositories;
import com.hotel.gestion.dto.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservasRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByFechaReserva(LocalDate fechaReserva);
}

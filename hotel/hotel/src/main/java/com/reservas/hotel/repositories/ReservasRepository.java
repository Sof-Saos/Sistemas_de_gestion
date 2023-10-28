package com.reservas.hotel.repositories;
import com.reservas.hotel.dto.Habitacion;
import com.reservas.hotel.dto.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservasRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT r FROM Reserva r WHERE r.fecha_reserva = :fecha")
    List<Reserva> findByFechaReserva(@Param("fecha") Date fecha);
    List<Reserva> findByFechaReservaAndTipoHabitacion(@Param("fecha") Date fecha, @Param("tipo") String tipo);
    List<Reserva> findByHabitacion(Habitacion habitacion);
    List<Reserva> findByDescuento(double descuento);

}
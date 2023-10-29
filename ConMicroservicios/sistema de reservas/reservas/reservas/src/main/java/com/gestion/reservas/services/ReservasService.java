package com.gestion.reservas.services;
import com.gestion.reservas.dto.*;
import com.gestion.reservas.feign.ClienteFeignClient;
import com.gestion.reservas.feign.HabitacionFeignClient;
import com.gestion.reservas.repositories.ReservasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservasService {
    @Autowired
    private final ClienteFeignClient clienteFeignClient;
    @Autowired
    private final HabitacionFeignClient habitacionFeignClient;
    @Autowired
    private ReservasRepository reservaRepository;

    public List<Habitacion> buscarHabitacionesDisponibles() {
        List<Habitacion> habsDisponibles = habitacionFeignClient.getHabitaciones();
        return habsDisponibles;
    }


    public List<Habitacion> filtrarPorTipo(String tipo) {
        return habitacionFeignClient.getHabitacionesByType(tipo);
    }

    public List<Habitacion> filtrarPorFecha(Date fecha) {
        List<Habitacion> disponibles = habitacionFeignClient.getHabitaciones();
        List<Reserva> reservasParaFecha = reservaRepository.findByFechaReserva(fecha);
        List<Habitacion> habitacionesReservadas = reservasParaFecha.stream()
                .map(Reserva::getHabitacion)
                .collect(Collectors.toList());
        List<Habitacion> habitacionesDisponibles = disponibles.stream()
                .filter(habitacion -> !habitacionesReservadas.contains(habitacion))
                .collect(Collectors.toList());
        return habitacionesDisponibles;
    }

    public Reserva crearReserva(Reserva reserva) {
        habitacionFeignClient.eliminarHabitacion(reserva.getHabitacion());
        return reservaRepository.save(reserva);
    }

    /*

    public boolean habitacionDisponibleParaFecha(Habitacion habitacion, LocalDate fechaReserva) {
    }

    public double calcularValorReserva(Habitacion habitacion, LocalDate fechaReserva) {
    } */
}

package com.reservas.hotel.controllers;

import com.reservas.hotel.dto.*;
import com.reservas.hotel.repositories.*;
import com.reservas.hotel.services.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservas")
public class ReservasController {
    @Autowired
    private ReservaServiceImpl reservaService;

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private ClienteRepository clienteRepository; // Inject the Cliente repository

    @Autowired
    private HabitacionRepository habitacionRepository; // Inject the Habitacion repository

    // Endpoint to search available rooms by date
    @GetMapping("/habitaciones-disponibles")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesDisponiblesPorFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        try {
            List<Habitacion> habitacionesDisponibles = reservaService.buscarHabitacionesDisponiblesPorFecha(fecha);
            return ResponseEntity.ok(habitacionesDisponibles);
        } catch (Exception e) {
            // Imprimir mensaje directamente en la consola
            System.err.println("Error al buscar habitaciones disponibles: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to search available rooms by date and type
    @GetMapping("/habitaciones-disponibles-por-tipo")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesDisponiblesPorTipo(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha,
            @RequestParam("tipo") String tipo) {
        try {
            List<Habitacion> habitacionesDisponibles = reservaService.buscarHabitacionesDisponiblesPorTipoYFecha(fecha, tipo);
            return ResponseEntity.ok(habitacionesDisponibles);
        } catch (Exception e) {
            // Imprimir mensaje directamente en la consola
            System.err.println("Error al buscar habitaciones disponibles por tipo: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarReserva(@RequestBody ReservaRequest reservaRequest) {
        try {
            // Código de reserva...
            Reserva reserva = new Reserva(); // Agrega esta línea para declarar la variable reserva

            // Imprimir mensaje directamente en la consola
            System.out.println("Reserva registrada con éxito. Código de reserva: " + reserva.getCodigoReserva());

            return new ResponseEntity<>(reserva.getCodigoReserva(), HttpStatus.CREATED);
        } catch (Exception e) {
            // Imprimir mensaje directamente en la consola
            System.err.println("Error al registrar la reserva: " + e.getMessage());

            return new ResponseEntity<>("Error durante la reserva.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
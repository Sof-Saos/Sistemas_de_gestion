package com.hotel.gestion.controllers;

import com.hotel.gestion.dto.*;
import com.hotel.gestion.repositories.ClienteRepository;
import com.hotel.gestion.repositories.HabitacionRepository;
import com.hotel.gestion.services.ReservasService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/v1/reservas")
@RequiredArgsConstructor
public class ReservasController {

    private ReservasService reservaService;

    private ClienteRepository clienteRepository;
    private HabitacionRepository habitacionRepository;

    public ReservasController(ReservasService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/habitaciones-disponibles")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesDisponibles() {
        List<Habitacion> habitacionesDisponibles = reservaService.buscarHabitacionesDisponibles();
        return ResponseEntity.ok(habitacionesDisponibles);
    }

    @GetMapping("/habitaciones-disponibles-por-tipo")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesDisponiblesPorTipo(@RequestParam("tipo") String tipo) {
        List<Habitacion> habitacionesDisponibles = reservaService.buscarHabitacionesDisponiblesPorTipo(tipo);
        return ResponseEntity.ok(habitacionesDisponibles);
    }

    @GetMapping("/habitaciones-disponibles-por-fecha")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesDisponiblesPorTipo(@RequestParam("fecha") Date fecha) {
        List<Habitacion> habitacionesDisponibles = reservaService.buscarHabitacionesDisponiblesPorFecha(fecha);
        return ResponseEntity.ok(habitacionesDisponibles);
    }

    @PostMapping("/register")
    public ResponseEntity<String> crearReserva(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha, @RequestParam("codigo") String codigo, @RequestParam("cedula") Long cedula, @RequestParam("idHab") Long id, @RequestParam("tipo") String tipo) {
        Reserva nuevaReserva = reservaService.realizarReserva(fecha, codigo, cedula, id, tipo);
        return ResponseEntity.ok("Reserva creada con éxito");
    }


}

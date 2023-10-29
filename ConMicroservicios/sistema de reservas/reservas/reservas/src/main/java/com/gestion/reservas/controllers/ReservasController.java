package com.gestion.reservas.controllers;

import com.gestion.reservas.services.ReservasService;
import com.gestion.reservas.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/v1/reservas")
@RequiredArgsConstructor
public class ReservasController {

    private ReservasService reservaService;

    public ReservasController(ReservasService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/habitaciones/disponibles")
    public List<Habitacion> buscarHabitacionesDisponibles() {
        return reservaService.buscarHabitacionesDisponibles();
        }

    @GetMapping("/habitaciones/disponibles/fecha")
    public List<Habitacion> filtrarPorFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        return reservaService.filtrarPorFecha(fecha);
    }
    @GetMapping("/habitaciones/disponibles/tipo")
    public List<Habitacion> filtrarPorTipo(@RequestParam(value = "tipo", required = false) String tipoHabitacion) {
        return reservaService.filtrarPorTipo(tipoHabitacion);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearReserva(@RequestBody Reserva reserva) {
        Reserva nuevaReserva = reservaService.crearReserva(reserva);
        return ResponseEntity.ok("Reserva creada con éxito");
    }

    /*
    @GetMapping("/habitaciones-disponibles")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesDisponiblesPorFecha(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha) {
        List<Habitacion> habitacionesDisponibles = reservaService.buscarHabitacionesDisponiblesPorFecha(fecha);
        return ResponseEntity.ok(habitacionesDisponibles);
    }


    @GetMapping("/habitaciones-disponibles-por-tipo")
    public ResponseEntity<List<Habitacion>> buscarHabitacionesDisponiblesPorTipo(
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fecha,
            @RequestParam("tipo") String tipo) {
        List<Habitacion> habitacionesDisponibles = reservaService.buscarHabitacionesDisponiblesPorTipo(fecha, tipo);
        return ResponseEntity.ok(habitacionesDisponibles);
    }
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarReserva(@RequestBody ReservaRequest reservaRequest) {
        // Perform necessary validations
        if (reservaRequest.getClienteId() == null || reservaRequest.getFechaReserva() == null) {
            return new ResponseEntity<>("Cliente y fecha de reserva son obligatorios.", HttpStatus.BAD_REQUEST);
        }

        // Verify if the client is registered
        Cliente cliente = clienteRepository.findById(reservaRequest.getClienteId()).orElse(null);
        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado en la base de datos.", HttpStatus.BAD_REQUEST);
        }

        // Verify if the date is valid (not in the past)
        LocalDate fechaReserva = reservaRequest.getFechaReserva().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        if (fechaReserva.isBefore(fechaActual)) {
            return new ResponseEntity<>("La fecha de reserva no puede ser en el pasado.", HttpStatus.BAD_REQUEST);
        }

        // Verify if the room is available for that date
        Habitacion habitacion = habitacionRepository.findById(reservaRequest.getHabitacionId()).orElse(null);
        if (habitacion == null) {
            return new ResponseEntity<>("Habitación no encontrada en la base de datos.", HttpStatus.BAD_REQUEST);
        }

        if (!reservaService.habitacionDisponibleParaFecha(habitacion, fechaReserva)) {
            return new ResponseEntity<>("La habitación no está disponible para la fecha seleccionada.", HttpStatus.BAD_REQUEST);
        }

        // Calculate the payment amount
        double valorAPagar = reservaService.calcularValorReserva(habitacion, fechaReserva);

        // Create the reservation
        Reserva reserva = new Reserva();
        reserva.setFecha_reserva(reservaRequest.getFechaReserva());
        reserva.setHabitacion(habitacion);
        reserva.setCliente(cliente);
        reserva.setCodigoReserva(UUID.randomUUID().toString());
        reserva.setTotal_pago(valorAPagar);

        // Save the reservation in the database
        reserva = reservasRepository.save(reserva);

        return new ResponseEntity<>(reserva.getCodigoReserva(), HttpStatus.CREATED);
    }  */
}

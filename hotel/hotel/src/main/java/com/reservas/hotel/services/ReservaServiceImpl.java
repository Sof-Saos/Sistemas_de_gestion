package com.reservas.hotel.services;

import com.reservas.hotel.repositories.*;
import com.reservas.hotel.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl {

    @Autowired
    private ReservasRepository reservasRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Habitacion> buscarHabitacionesDisponiblesPorFecha(Date fecha) {
        List<Reserva> reservasEnFecha = reservasRepository.findByFechaReserva(fecha);

        List<Habitacion> habitacionesDisponibles = habitacionRepository.findAll();

        for (Reserva reserva : reservasEnFecha) {
            habitacionesDisponibles.remove(reserva.getHabitacion());
        }

        return habitacionesDisponibles;
    }

    public List<Habitacion> buscarHabitacionesDisponiblesPorTipoYFecha(Date fecha, String tipo) {
        List<Reserva> reservasEnFechaYTipo = reservasRepository.findByFechaReservaAndTipoHabitacion(fecha, tipo);

        List<Habitacion> habitacionesDelTipo = habitacionRepository.findByTipo(tipo);

        return habitacionesDelTipo.stream()
                .filter(habitacion -> habitacionDisponibleParaFecha(habitacion, fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()))
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> registrarReserva(ReservaRequest reservaRequest) {
        // Validar que los campos obligatorios no sean nulos
        if (reservaRequest.getClienteId() == null || reservaRequest.getFechaReserva() == null) {
            return new ResponseEntity<>("Cliente y fecha de reserva son obligatorios.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si el cliente está registrado
        Cliente cliente = clienteRepository.findById(reservaRequest.getClienteId()).orElse(null);
        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado en la base de datos.", HttpStatus.BAD_REQUEST);
        }

        // Convertir la fecha de reserva a LocalDate
        LocalDate fechaReserva = reservaRequest.getFechaReserva().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Verificar si la fecha de reserva es válida (no en el pasado)
        LocalDate fechaActual = LocalDate.now();
        if (fechaReserva.isBefore(fechaActual)) {
            return new ResponseEntity<>("La fecha de reserva no puede ser en el pasado.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si la habitación está disponible para esa fecha
        Habitacion habitacion = habitacionRepository.findById(reservaRequest.getHabitacionId()).orElse(null);
        if (habitacion == null) {
            return new ResponseEntity<>("Habitación no encontrada en la base de datos.", HttpStatus.BAD_REQUEST);
        }

        if (!habitacionDisponibleParaFecha(habitacion, fechaReserva)) {
            return new ResponseEntity<>("La habitación no está disponible para la fecha seleccionada.", HttpStatus.BAD_REQUEST);
        }

        // Calcular el valor a pagar
        double valorAPagar = calcularValorReserva(habitacion, fechaReserva);

        // Crear la reserva
        Reserva reserva = new Reserva();
        reserva.setFecha_reserva(reservaRequest.getFechaReserva());
        reserva.setHabitacion(habitacion);
        reserva.setCliente(cliente);
        reserva.setCodigoReserva(UUID.randomUUID().toString());
        reserva.setTotal_pago(valorAPagar);

        // Guardar la reserva en la base de datos
        reserva = reservasRepository.save(reserva);

        // Devolver la confirmación de la reserva
        return new ResponseEntity<>(reserva.getCodigoReserva(), HttpStatus.CREATED);
    }


    public boolean habitacionDisponibleParaFecha(Habitacion habitacion, LocalDate fechaReserva) {
        // Implementa la lógica para verificar si la habitación está disponible para la fecha
        return false;
    }

    public double calcularValorReserva(Habitacion habitacion, LocalDate fechaReserva) {
        // Implementa la lógica para calcular el valor de la reserva
        return 0;
    }
    // Nuevo método para calcular el valor de la reserva con descuentos
    public double calcularValorReserva(Habitacion habitacion, LocalDate fechaReserva, String tipoCliente) {
        // Obtener el precio base de la habitación
        double precioBase = habitacion.getPrecioBase(); // Ajusta aquí al nombre correcto del atributo

        // Calcular la diferencia de días entre la fecha actual y la de reserva
        long diasDiferencia = ChronoUnit.DAYS.between(LocalDate.now(), fechaReserva);

        // Aplicar descuentos según las reglas especificadas
        if (diasDiferencia > 15) {
            precioBase *= 0.8; // Descuento del 20% por más de 15 días
        }

        if ("premium".equalsIgnoreCase(tipoCliente)) {
            precioBase *= 0.95; // Descuento adicional del 5% para clientes premium
        }

        return precioBase;
    }

    public List<Reserva> obtenerReservasDeCliente(Long clienteId) {
        return null;
    }
}
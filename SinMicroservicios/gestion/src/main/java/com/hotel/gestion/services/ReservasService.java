package com.hotel.gestion.services;

import com.hotel.gestion.dto.Cliente;
import com.hotel.gestion.dto.Habitacion;
import com.hotel.gestion.dto.Reserva;
import com.hotel.gestion.exceptions.DatosInvalidosException;
import com.hotel.gestion.repositories.ClienteRepository;
import com.hotel.gestion.repositories.HabitacionRepository;
import com.hotel.gestion.repositories.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ReservasService {
    private final ReservasRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ReservasService(ClienteRepository clienteRepository, HabitacionRepository habitacionRepository, ReservasRepository reservaRepository) {
        this.clienteRepository = clienteRepository;
        this.habitacionRepository = habitacionRepository;
        this.reservaRepository = reservaRepository;
    }

    public double calcularPrecioTotal(Habitacion habitacion) {
        double precioBase = habitacion.getPrecioBase();
        String tipo = habitacion.getTipo();
        if (tipo.equalsIgnoreCase("ESTANDAR")) {
            double descuento = precioBase * 0.2;
            return precioBase - descuento;
        } else {
            double descuento = precioBase * 0.25;
            return precioBase - descuento;
        }
    }

    public List<Habitacion> buscarHabitacionesDisponibles() {
        return habitacionRepository.findAll();
    }

    public List<Reserva> verReservasCliente(long cedula) {
        List<Reserva> listaDeTodasLasReservas = reservaRepository.findAll();
        if (listaDeTodasLasReservas.isEmpty()) {
            throw new DatosInvalidosException("El cliente con cedula=" + cedula + " no ha realizado ninguna reserva");
        }
        return listaDeTodasLasReservas.stream()
                .filter(reserva -> reserva.getCliente().getCedula() == cedula)
                .collect(Collectors.toList());
    }

    public List<Reserva> verTodasReservas() {
        List<Reserva> listaCompleta = reservaRepository.findAll();
        return listaCompleta;
    }

    public Reserva realizarReserva(Date fecha, String codigo, Long cedula, Long id, String tipo) {
        Random random = new Random();
        Long reservaId = random.nextLong();
        Cliente cliente = clienteRepository.findById(cedula)
                .orElseThrow(() -> new DatosInvalidosException("Cliente no encontrado con cédula: " + cedula));
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new DatosInvalidosException("Habitación no encontrada con ID: " + id));
        double precio = calcularPrecioTotal(habitacion);
        Reserva newReserva = new Reserva(reservaId, fecha, codigo, cliente, habitacion, precio);
        return reservaRepository.save(newReserva);
    }


    public List<Habitacion> buscarHabitacionesDisponiblesPorTipo(String tipo) {
        return habitacionRepository.getHabitacionesByType(tipo);
    }

    public List<Habitacion> buscarHabitacionesDisponiblesPorFecha(Date fechaReserva) {
        return habitacionRepository.getHabitacionesByFecha(fechaReserva);
    }
}


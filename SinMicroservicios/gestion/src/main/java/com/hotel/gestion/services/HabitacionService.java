package com.hotel.gestion.services;

import com.hotel.gestion.dto.Habitacion;
import com.hotel.gestion.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;


@Service
public class HabitacionService {

    private HabitacionRepository repository;

    @Autowired
    public HabitacionService(HabitacionRepository repository) {
        this.repository = repository;
    }

    public HabitacionService() {
    }

    public String registrarHabitaciones() {
        registrarHabitacionesEstandar();
        registrarHabitacionesPremium();
        return "Habitaciones registradas con exito.";
    }

    public boolean registrarHabitacionesPremium() {
        double precioBasePremium = 90000.0;
        boolean habitacionesRegistradas = true;

        for (int i = 1; i <= 5; i++) {
            Long idHab = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            Habitacion premium = new Habitacion(idHab, "premium", precioBasePremium);
            if (repository.save(premium) == null) {
                habitacionesRegistradas = false;
                break;
            }
        }

        return habitacionesRegistradas;
    }

    public boolean registrarHabitacionesEstandar() {
        double precioBaseEstandar = 30000.0;
        boolean habitacionesRegistradas = true;

        for (int i = 6; i <= 10; i++) {
            Long idHab = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            Habitacion premium = new Habitacion(idHab, "estandar", precioBaseEstandar);
            if (repository.save(premium) == null) {
                habitacionesRegistradas = false;
                break;
            }
        }

        return habitacionesRegistradas;
    }


    public List<Habitacion> getHabitaciones() {
        return repository.findAll();
    }

    public void eliminarHabitacion(Habitacion habitacion) {
        repository.delete(habitacion);
    }

    public Habitacion crearHabitacion(Habitacion habitacion) {
        return repository.save(habitacion);
    }

    public List<Habitacion> getHabitacionesByType(String type) {
        return repository.getHabitacionesByType(type);
    }

}

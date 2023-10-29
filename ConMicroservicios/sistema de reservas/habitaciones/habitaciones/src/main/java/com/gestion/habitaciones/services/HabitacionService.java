package com.gestion.habitaciones.services;

import com.gestion.habitaciones.dto.Habitacion;
import com.gestion.habitaciones.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository repository;

    public boolean registrarHabitaciones() {
        boolean habitacionesRegistradas = false;

        boolean premiumRegistradas = registrarHabitacionesPremium();
        boolean estandarRegistradas = registrarHabitacionesEstandar();

        if (premiumRegistradas && estandarRegistradas) {
            habitacionesRegistradas = true;
        }

        return habitacionesRegistradas;
    }

    public boolean registrarHabitacionesPremium() {
        double precioBasePremium = 90000.0;
        boolean habitacionesRegistradas = true;

        for (int i = 1; i <= 5; i++) {
            Habitacion premium = new Habitacion(i, "premium", precioBasePremium);
            if (repository.save(premium) == null) {
                habitacionesRegistradas = false;
                break; // Salir del bucle si no se pudo guardar una habitación
            }
        }

        return habitacionesRegistradas;
    }

    public boolean registrarHabitacionesEstandar() {
        double precioBaseEstandar = 30000.0;
        boolean habitacionesRegistradas = true;

        for (int i = 6; i <= 10; i++) {
            Habitacion estandar = new Habitacion(i, "estandar", precioBaseEstandar);
            if (repository.save(estandar) == null) {
                habitacionesRegistradas = false;
                break; // Salir del bucle si no se pudo guardar una habitación
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

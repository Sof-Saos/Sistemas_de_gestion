package com.gestion.habitaciones.services;

import com.gestion.habitaciones.dto.Habitacion;
import com.gestion.habitaciones.repositories.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository repository;

    public void registrarHabitaciones() {
        registrarHabitacionesPremium();
        registrarHabitacionesEstandar();
    }

    public void registrarHabitacionesPremium() {
        double precioBasePremium = 90000.0;
        for (int i = 1; i <= 5; i++) {
            Habitacion premium = new Habitacion(i, "premium", precioBasePremium);
            repository.save(premium);
        }
    }
    public void registrarHabitacionesEstandar() {
        double precioBaseEstandar = 30000.0;
        for (int i = 6; i <= 10; i++) {
            Habitacion estandar = new Habitacion(i, "estandar", precioBaseEstandar);
            repository.save(estandar);
        }
    }

}
package com.gestion.habitaciones.controllers;

import com.gestion.habitaciones.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/habs")
public class HabitacionController {

    @Autowired
    private HabitacionService service;

    //endpoint para registrar habitaciones
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarHabitaciones() {
        service.registrarHabitaciones();
        return ResponseEntity.ok("Habitaciones registradas con éxito.");
    }

}
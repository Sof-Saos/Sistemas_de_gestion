package com.hotel.gestion.controllers;

import com.hotel.gestion.dto.Habitacion;
import com.hotel.gestion.services.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/habs")
public class HabitacionController {


    private HabitacionService service;

    @Autowired
    public HabitacionController(HabitacionService service) {
        this.service = service;
    }


    @PostMapping("/registrar")
    public ResponseEntity<String> registrarHabitaciones() {
        this.service.registrarHabitaciones();
        return ResponseEntity.ok("Habitaciones registradas con éxito.");
    }

    public ResponseEntity<List<Habitacion>> obtenerHabitaciones() {
        List<Habitacion> habitaciones = this.service.getHabitaciones();
        return new ResponseEntity<>(habitaciones, HttpStatus.OK);
    }


}

package com.reservas.hotel.controllers;

import com.reservas.hotel.dto.Reserva;
import com.reservas.hotel.services.ReservaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/reservas")
public class Endpoint {

    @Autowired
    private ReservaServiceImpl reservaService;

    public void ReservaController(ReservaServiceImpl reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/cliente/{clienteId}/reservas")
    public ResponseEntity<String> obtenerReservasDeCliente(@PathVariable Long clienteId) {
        List reservas = (List) reservaService.obtenerReservasDeCliente(clienteId);

        if (((java.util.List<Reserva>) reservas).isEmpty()) {
            return new ResponseEntity<String>("No se encontraron reservas para el cliente con ID " + clienteId, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>(String.valueOf(reservas), HttpStatus.OK);
    }
}
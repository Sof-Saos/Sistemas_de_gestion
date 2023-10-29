package com.hotel.gestion.controllers;

import com.hotel.gestion.dto.Cliente;
import com.hotel.gestion.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
        this.clienteService.registrarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/showall")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = this.clienteService.obtenerTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

}

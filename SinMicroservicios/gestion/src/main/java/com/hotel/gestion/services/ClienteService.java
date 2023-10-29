package com.hotel.gestion.services;
import com.hotel.gestion.dto.Cliente;
import com.hotel.gestion.exceptions.DatosInvalidosException;
import com.hotel.gestion.exceptions.DuplicadoException;
import com.hotel.gestion.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteService() {
    }

    public Cliente registrarCliente(Cliente cliente) {
        if (!esCedulaNumerica(cliente.getCedula())) {
            throw new DatosInvalidosException("La cedula debe ser de caracter numerico.");
        }
        if (repository.existsById(cliente.getCedula())) {
            throw new DuplicadoException("La cédula ya existe en la base de datos.");
        }
        if (cliente.getNombre() == null || cliente.getApellido() == null) {
            throw new DatosInvalidosException("Nombre y apellido no pueden ser nulos.");
        }

        return this.repository.save(cliente);
    }

    private boolean esCedulaNumerica(Long cedula) {
        String cedulaStr = cedula.toString();
        return cedulaStr.matches("\\d+");
    }

    public List<Cliente> obtenerTodos() {
        return repository.findAll();
    }

    public Cliente getById(Long cedula) {
        return repository.getById(cedula);
    }
}

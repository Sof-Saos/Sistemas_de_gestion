package com.gestion.clientes.services;
import com.gestion.clientes.dto.Cliente;
import com.gestion.clientes.exceptions.DatosInvalidosException;
import com.gestion.clientes.exceptions.DuplicadoException;
import com.gestion.clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

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

        return repository.save(cliente);
    }

    private boolean esCedulaNumerica(Long cedula) {
        String cedulaStr = cedula.toString();
        return cedulaStr.matches("\\d+");
    }
}

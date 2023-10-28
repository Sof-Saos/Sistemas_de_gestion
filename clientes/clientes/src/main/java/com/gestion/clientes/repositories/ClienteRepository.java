package com.gestion.clientes.repositories;
import com.gestion.clientes.dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
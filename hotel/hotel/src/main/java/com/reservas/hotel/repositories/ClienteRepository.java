package com.reservas.hotel.repositories;
import com.reservas.hotel.dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
package com.hotel.gestion.repositories;
import com.hotel.gestion.dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

package com.gestion.reservas.feign;

import com.gestion.reservas.dto.Habitacion;
import org.springframework.http.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@FeignClient(name = "habitaciones", url = "localhost:8091")
public interface HabitacionFeignClient {

    @PostMapping(value = "/habitaciones/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean registrarHabitaciones();

    @GetMapping(value = "/habitaciones", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Habitacion> getHabitaciones();

    @GetMapping(value = "/habitaciones/type", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Habitacion> getHabitacionesByType(String tipo);

    @PostMapping(value = "/habitaciones/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    Habitacion crearHabitacion(Habitacion habitacion);

    @PostMapping(value = "/habitaciones/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    void eliminarHabitacion(Habitacion habitacion);
}

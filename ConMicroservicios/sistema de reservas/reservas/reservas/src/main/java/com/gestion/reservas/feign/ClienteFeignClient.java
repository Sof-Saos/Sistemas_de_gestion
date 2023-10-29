package com.gestion.reservas.feign;

import com.gestion.reservas.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;
@FeignClient(name = "clientes", url = "localhost:8090")
public interface ClienteFeignClient {
    @GetMapping(name = "/clientes/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    Cliente registrarCliente(Cliente cliente);
}

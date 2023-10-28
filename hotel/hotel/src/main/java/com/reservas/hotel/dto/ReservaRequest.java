package com.reservas.hotel.dto;

import java.util.Date;

public class ReservaRequest {
    private Long clienteId; // El ID del cliente que realiza la reserva
    private Long habitacionId; // El ID de la habitación que se reserva
    private Date fechaReserva; // La fecha de la reserva

    // Agrega los constructores, getters y setters según tus necesidades

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getHabitacionId() {
        return habitacionId;
    }

    public void setHabitacionId(Long habitacionId) {
        this.habitacionId = habitacionId;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
package com.reservas.hotel.dto;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date fecha_reserva;

    @Column
    private String codigoReserva;

    @Column
    private double total_pago;

    @Column(name = "descuento")
    private double descuento;

    @ManyToOne()
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    public Reserva() {
    }

    public Reserva(Long id, Date fecha_reserva, String codigoReserva, double total_pago, Cliente cliente, Habitacion habitacion) {
        this.id = id;
        this.fecha_reserva = fecha_reserva;
        this.codigoReserva = codigoReserva;
        this.total_pago = total_pago;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public double getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(double total_pago) {
        this.total_pago = total_pago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
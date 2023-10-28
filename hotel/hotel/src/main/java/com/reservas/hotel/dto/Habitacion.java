package com.reservas.hotel.dto;
import javax.persistence.*;


@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long numero;

    @Column(name = "tipo_habitacion")
    private String tipo;

    @Column(name = "precioBase")
    private double precioBase;

    public Habitacion() {
    }

    public Habitacion(long numero, String tipo, double precioBase) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    @OneToOne(mappedBy = "habitacion")
    private Reserva reserva;

}
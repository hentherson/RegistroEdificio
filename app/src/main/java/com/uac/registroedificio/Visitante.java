package com.uac.registroedificio;

import java.io.Serializable;

public class Visitante implements Serializable {
    private String nombre;
    private String id;
    private String apartamento;
    private String tipoVisita;
    private String fechaHora;

    public Visitante(String nombre, String id, String apartamento, String fechahora, String tipovisita) {
        this.nombre = nombre;
        this.id = id;
        this.apartamento = apartamento;
        this.tipoVisita = tipovisita;
        this.fechaHora = fechahora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Visitante{" +
                "nombre='" + nombre + '\'' +
                ", identificación='" + id + '\'' +
                ", apartamento='" + apartamento + '\'' +
                ", tipovisita='" + tipoVisita + '\'' +
                ", fechahora='" + fechaHora + '\'' +
                '}';
    }
}

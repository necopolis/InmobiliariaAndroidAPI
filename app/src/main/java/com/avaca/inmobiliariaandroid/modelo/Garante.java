package com.avaca.inmobiliariaandroid.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Garante implements Serializable {

    private int id;
    private Long dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String lugarTrabajo;
    private double sueldo;
    private boolean activo;

    public Garante() {
    }

    public Garante(int id, Long dni, String nombre, String apellido, String telefono, String email, String lugarTrabajo, double sueldo, boolean activo) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.lugarTrabajo = lugarTrabajo;
        this.sueldo = sueldo;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Garante)) return false;
        Garante garante = (Garante) o;
        return getId() == garante.getId() && Objects.equals(getDni(), garante.getDni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDni());
    }

    @Override
    public String toString() {
        return "Garante{" +
                "id=" + id +
                ", dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", lugarTrabajo='" + lugarTrabajo + '\'' +
                ", sueldo=" + sueldo +
                ", activo=" + activo +
                '}';
    }
}

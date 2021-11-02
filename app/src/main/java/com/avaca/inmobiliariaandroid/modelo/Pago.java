package com.avaca.inmobiliariaandroid.modelo;

import java.io.Serializable;

public class Pago implements Serializable {

    private int id;
    private int numeroPago;
    private String fechaPago;
    private double importe;
    private int contratoId;
    private Contrato contrato;

    public Pago() {
    }

    public Pago(int id, int numeroPago, String fechaPago, double importe, int contratoId, Contrato contrato) {
        this.id = id;
        this.numeroPago = numeroPago;
        this.fechaPago = fechaPago;
        this.importe = importe;
        this.contratoId = contratoId;
        this.contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(int numeroPago) {
        this.numeroPago = numeroPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}

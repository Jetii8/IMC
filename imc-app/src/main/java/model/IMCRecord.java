package com.example.models;

import java.time.LocalDateTime;

public class IMCRecord {
    private int idImc;
    private double imc;
    private LocalDateTime creadoEn;
    private int usuarioIdUsuario; // Foreign key reference

    // Constructors, getters, and setters
    public IMCRecord() {
    }

    public IMCRecord(int idImc, double imc, LocalDateTime creadoEn, int usuarioIdUsuario) {
        this.idImc = idImc;
        this.imc = imc;
        this.creadoEn = creadoEn;
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    // Getters and setters
    public int getIdImc() {
        return idImc;
    }

    public void setIdImc(int idImc) {
        this.idImc = idImc;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public int getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(int usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }
}

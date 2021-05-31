package com.example.clublaribera_app.modelos;

import java.io.Serializable;

public class Grupo implements Serializable {
    private int id;
    private String nombreGrupo;
    private int idResponsable;
    private Usuario usuario;

    public Grupo() {
    }

    public Grupo(int id, String nombreGrupo) {
        this.id = id;
        this.nombreGrupo = nombreGrupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return nombreGrupo;
    }
}

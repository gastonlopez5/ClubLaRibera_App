package com.example.clublaribera_app.modelos;

import java.io.Serializable;

public class Grupo implements Serializable {
    private int id;
    private String nombreGrupo;
    private Usuario usuario;

    public Grupo() {
    }

    public Grupo(int id, String nombreGrupo, Usuario usuario) {
        this.id = id;
        this.nombreGrupo = nombreGrupo;
        this.usuario = usuario;
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
}

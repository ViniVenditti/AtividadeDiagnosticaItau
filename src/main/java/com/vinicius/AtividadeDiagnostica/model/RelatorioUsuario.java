package com.vinicius.AtividadeDiagnostica.model;

import com.vinicius.AtividadeDiagnostica.entity.Usuario;

import java.time.LocalDateTime;

public class RelatorioUsuario {

    private LocalDateTime totalHoras;

    private Usuario usuario;

    public LocalDateTime getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(LocalDateTime totalHoras) {
        this.totalHoras = totalHoras;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

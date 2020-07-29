package com.vinicius.AtividadeDiagnostica.model;

import com.vinicius.AtividadeDiagnostica.entity.Usuario;

import java.time.LocalDateTime;

public class RelatorioUsuario {

    private Long totalHoras;

    private Usuario usuario;

    public Long getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(Long totalHoras) {
        this.totalHoras = totalHoras;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

package com.vinicius.AtividadeDiagnostica.model;

public class PontoModel {

    private String tipoBatida;
    private UsuarioModel usuarioModel;

    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public String getTipoBatida() {
        return tipoBatida;
    }

    public void setTipoBatida(String tipoBatida) {
        this.tipoBatida = tipoBatida;
    }
}

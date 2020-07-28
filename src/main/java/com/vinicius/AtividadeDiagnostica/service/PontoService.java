package com.vinicius.AtividadeDiagnostica.service;

import com.vinicius.AtividadeDiagnostica.entity.Ponto;
import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.model.PontoModel;
import com.vinicius.AtividadeDiagnostica.model.RelatorioUsuario;
import com.vinicius.AtividadeDiagnostica.repository.PontoBanco;
import com.vinicius.AtividadeDiagnostica.repository.UsuarioBanco;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PontoService {

    private PontoBanco pontoBanco;
    private UsuarioBanco usuarioBanco;

    public PontoService(PontoBanco pontoBanco, UsuarioBanco usuarioBanco){
        this.pontoBanco = pontoBanco;
        this.usuarioBanco = usuarioBanco;
    }

    public Ponto marcarPonto(Long idUsuario, Ponto ponto) {
        return this.pontoBanco.save(ponto);
    }

    public RelatorioUsuario obterRelatorioUsuario(Long idUsuario) {
        List<Ponto> listaPontos = this.pontoBanco.findPontoByUsuario(idUsuario);
        Optional<Usuario> user = this.usuarioBanco.findById(idUsuario);
        RelatorioUsuario relatorio = new RelatorioUsuario();


        return null;
    }

}

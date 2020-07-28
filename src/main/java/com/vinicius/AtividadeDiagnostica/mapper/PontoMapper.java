package com.vinicius.AtividadeDiagnostica.mapper;

import com.vinicius.AtividadeDiagnostica.entity.Ponto;
import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.model.PontoModel;
import com.vinicius.AtividadeDiagnostica.repository.UsuarioBanco;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PontoMapper {

    private UsuarioBanco banco;

    private PontoMapper(UsuarioBanco banco){
        this.banco = banco;
    }

    public Ponto from (PontoModel model) {
        Ponto ponto = new Ponto();
        ponto.setHorario(LocalDateTime.now());
        ponto.setTipoBatida(model.getTipoBatida());

        Optional<Usuario> user = this.banco.findById(model.getUsuarioModel().getId());
        ponto.setUsuario(user.orElseGet(Usuario::new));

        return ponto;
    }

}

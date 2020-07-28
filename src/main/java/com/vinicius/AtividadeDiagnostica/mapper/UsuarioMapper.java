package com.vinicius.AtividadeDiagnostica.mapper;

import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.model.UsuarioModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UsuarioMapper {

    private UsuarioMapper(){}

    public UsuarioModel to (Usuario response) {
        UsuarioModel user = new UsuarioModel();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        user.setId(response.getUsuario_id());
        user.setNome(response.getNomeCompleto());
        user.setCpf(response.getCpf());
        user.setEmail(response.getEmail());
        user.setDataCadastro(LocalDate.parse(response.getDataCadastro(), formatter));

        return user;
    }

    public Usuario from (UsuarioModel model) {
        Usuario user = new Usuario();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        user.setNomeCompleto(model.getNome());
        user.setCpf(model.getCpf());
        user.setEmail(model.getEmail());
        user.setDataCadastro(LocalDate.now().format(formatter));

        return user;
    }
}

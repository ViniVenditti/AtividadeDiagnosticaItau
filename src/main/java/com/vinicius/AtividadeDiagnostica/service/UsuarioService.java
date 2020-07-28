package com.vinicius.AtividadeDiagnostica.service;

import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.mapper.UsuarioMapper;
import com.vinicius.AtividadeDiagnostica.model.UsuarioModel;
import com.vinicius.AtividadeDiagnostica.repository.UsuarioBanco;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private UsuarioBanco banco;
    private UsuarioMapper mapper;

    public UsuarioService(UsuarioBanco banco, UsuarioMapper mapper){
        this.banco = banco;
        this.mapper = mapper;
    }

    public UsuarioModel criarUsuario(Usuario user){
        Usuario response = this.banco.save(user);

        return mapper.to(response);
    }

    public List<UsuarioModel> listaUsuarios() {
        List<Usuario> lista = this.banco.findAll();
        return lista
                .stream()
                .map(usuario -> mapper.to(usuario))
                .collect(Collectors.toList());
    }

    public UsuarioModel buscarUsuario(Long usuarioId) {
        Optional<Usuario> user = this.banco.findById(usuarioId);
        if(user.isPresent()){
            return mapper.to(user.get());
        }
        return null;
    }

    public UsuarioModel atualizarUsuario(Long usuarioId, UsuarioModel usuarioModel) {
        Optional<Usuario> user = this.banco.findById(usuarioId);
        if(user.isPresent()){
            Usuario usuario = user.get();
            usuario.setEmail(StringUtils.isBlank(usuarioModel.getEmail()) ? usuario.getEmail() : usuarioModel.getEmail());
            usuario.setNomeCompleto(StringUtils.isBlank(usuarioModel.getNome()) ? usuario.getNomeCompleto() : usuarioModel.getNome());
            usuario.setCpf(StringUtils.isBlank(usuarioModel.getCpf()) ? usuario.getCpf() : usuarioModel.getCpf());
            Usuario newUser = this.banco.save(usuario);
            return mapper.to(newUser);
        } else
            throw new EmptyResultDataAccessException(1);
    }
}

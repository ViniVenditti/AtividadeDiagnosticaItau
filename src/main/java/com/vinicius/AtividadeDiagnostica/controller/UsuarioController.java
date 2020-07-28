package com.vinicius.AtividadeDiagnostica.controller;

import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.mapper.UsuarioMapper;
import com.vinicius.AtividadeDiagnostica.model.UsuarioModel;
import com.vinicius.AtividadeDiagnostica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value="/usuario")
@RestController
public class UsuarioController {

    private UsuarioMapper mapper;
    private UsuarioService service;

    public UsuarioController(UsuarioMapper mapper, UsuarioService service){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> listaUsuarios = service.listaUsuarios();
        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    @GetMapping(value = "/{usuarioId}")
    public ResponseEntity<UsuarioModel> detalhesUsuario(@PathVariable Long usuarioId){
        UsuarioModel user = service.buscarUsuario(usuarioId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/{usuarioId}")
    public ResponseEntity<UsuarioModel> atualizarUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioModel usuarioModel) {
        UsuarioModel user = service.atualizarUsuario(usuarioId, usuarioModel);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UsuarioModel> cadastrarUsuario(@Valid @RequestBody UsuarioModel usuario){
        Usuario entity = mapper.from(usuario);
        UsuarioModel userCreated = service.criarUsuario(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}

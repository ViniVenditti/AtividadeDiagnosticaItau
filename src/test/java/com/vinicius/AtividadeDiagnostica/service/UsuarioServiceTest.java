package com.vinicius.AtividadeDiagnostica.service;

import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.mapper.UsuarioMapper;
import com.vinicius.AtividadeDiagnostica.model.UsuarioModel;
import com.vinicius.AtividadeDiagnostica.repository.UsuarioBanco;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceTest {

    @Mock
    private UsuarioBanco usuarioBanco;

    @Mock
    private UsuarioMapper mapper;

    @InjectMocks
    private UsuarioService service;
    private Usuario user1;
    private Usuario user2;
    private UsuarioModel user3;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        user1 = new Usuario();
        user1.setNomeCompleto("vinicius Venditti");
        user1.setEmail("vini@teste.com");
        user1.setCpf("40078856499");
        user1.setDataCadastro("27/07/2020");

        user2 = new Usuario();
        user2.setNomeCompleto("Ronaldo");
        user2.setEmail("ronaldo@teste.com");
        user2.setCpf("77795641255");
        user2.setDataCadastro("27/07/2020");

        user3 = new UsuarioModel();
        user3.setNome("vinicius Venditti");
        user3.setEmail("vini@teste.com");
        user3.setCpf("40078856499");
        user3.setDataCadastro(LocalDate.now());


    }

    @Test
    public void deveRegistrarUsuario() throws Exception {
        Mockito.when(usuarioBanco.save(Mockito.any(Usuario.class))).thenReturn((new Usuario()));
        Mockito.when(mapper.to(Mockito.any(Usuario.class))).thenReturn(new UsuarioModel());
        UsuarioModel usuarioModel = service.criarUsuario(new Usuario());

        Assert.assertNotNull(usuarioModel);
    }

    @Test
    public void deveListaUsuarios() throws Exception {
        List<Usuario> lista = new ArrayList<Usuario>();
        lista.add(user1);
        lista.add(user2);

        Mockito.when(usuarioBanco.findAll()).thenReturn(lista);
        Mockito.when(mapper.to(Mockito.any(Usuario.class))).thenReturn(user3);

        List<UsuarioModel> usuarioModels = service.listaUsuarios();
        Assert.assertNotNull(usuarioModels);
    }

    @Test
    public void deveBuscarUsuarioPorId() throws Exception {
        Mockito.when(usuarioBanco.findById(Mockito.anyLong())).thenReturn(Optional.of(user1));
        Mockito.when(mapper.to(Mockito.any(Usuario.class))).thenReturn(user3);

        UsuarioModel usuarioModel = service.buscarUsuario(1l);
        Assert.assertNotNull(usuarioModel);
    }

    @Test
    public void deveAtualizarUsuario() throws Exception {
        Mockito.when(usuarioBanco.findById(Mockito.anyLong())).thenReturn(Optional.of(user1));
        Mockito.when(usuarioBanco.save(Mockito.any(Usuario.class))).thenReturn((new Usuario()));
        Mockito.when(mapper.to(Mockito.any(Usuario.class))).thenReturn(user3);

        UsuarioModel usuarioModel = service.atualizarUsuario(1l, user3);
        Assert.assertNotNull(usuarioModel);
    }
}

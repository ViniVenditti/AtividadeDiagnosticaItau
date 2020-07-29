package com.vinicius.AtividadeDiagnostica.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.mapper.UsuarioMapper;
import com.vinicius.AtividadeDiagnostica.model.UsuarioModel;
import com.vinicius.AtividadeDiagnostica.service.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioService service;

    @MockBean
    private UsuarioMapper mapper;

    private String requestJson;
    private UsuarioModel usuarioModel;

    @Before
    public void setup() throws JsonProcessingException {
        usuarioModel = new UsuarioModel();
        usuarioModel.setCpf("44477700011");
        usuarioModel.setEmail("vinicius@teste.com");
        usuarioModel.setNome("Vinicius");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        requestJson=ow.writeValueAsString(usuarioModel);
    }

    @Test
    public void deveRetornarListaUsuarios() throws Exception {
        List<UsuarioModel> lista = new ArrayList<UsuarioModel>();
        lista.add(usuarioModel);

        Mockito.when(service.listaUsuarios()).thenReturn(lista);
        mvc.perform(MockMvcRequestBuilders
                .get("/usuario")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
        verify(service, VerificationModeFactory.times(1)).criarUsuario(Mockito.any());
    }

    @Test
    public void deveRetornarDetalhesUsuario() throws Exception {
        Mockito.when(service.buscarUsuario(Mockito.anyLong())).thenReturn(usuarioModel);
        mvc.perform(MockMvcRequestBuilders
                .get("/usuario/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }

    @Test
    public void deveAtualizarUsuario() throws Exception {
        Mockito.when(service.atualizarUsuario(1L, usuarioModel)).thenReturn(new UsuarioModel());
        mvc.perform(MockMvcRequestBuilders
                .put("/usuario/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson))
            .andExpect(status().isOk());
    }

    @Test
    public void deveCadastrarNovoUsuario() throws Exception {
        Mockito.when(mapper.from(Mockito.any(UsuarioModel.class))).thenReturn(new Usuario());
        Mockito.when(service.criarUsuario(Mockito.any(Usuario.class))).thenReturn(new UsuarioModel());
        mvc.perform(MockMvcRequestBuilders
                .post("/usuario")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson))
            .andExpect(status().isCreated());

    }
}

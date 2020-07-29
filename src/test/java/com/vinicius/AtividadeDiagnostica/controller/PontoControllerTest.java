package com.vinicius.AtividadeDiagnostica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vinicius.AtividadeDiagnostica.entity.Ponto;
import com.vinicius.AtividadeDiagnostica.mapper.PontoMapper;
import com.vinicius.AtividadeDiagnostica.model.PontoModel;
import com.vinicius.AtividadeDiagnostica.model.RelatorioUsuario;
import com.vinicius.AtividadeDiagnostica.model.UsuarioModel;
import com.vinicius.AtividadeDiagnostica.service.PontoService;
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

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PontoController.class)
public class PontoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PontoMapper mapper;

    @MockBean
    private PontoService service;

    @Test
    public void deveRegistrarPonto() throws Exception {
        Mockito.when(mapper.from(Mockito.any(PontoModel.class))).thenReturn(new Ponto());
        Mockito.when(service.marcarPonto(Mockito.any(Ponto.class))).thenReturn(new Ponto());

        PontoModel model = new PontoModel();
        model.setTipoBatida("ENTRADA");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(model);

        mvc.perform(MockMvcRequestBuilders
                .post("/ponto")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestJson))
            .andExpect(status().isOk());
        verify(service, VerificationModeFactory.times(1)).marcarPonto(Mockito.any());
    }

    @Test
    public void deveRetornarRelatorioPorUsuario() throws Exception {
        Mockito.when(service.obterRelatorioUsuario(1l)).thenReturn(new RelatorioUsuario());
        mvc.perform(MockMvcRequestBuilders
                .get("/ponto/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk());
    }
}

package com.vinicius.AtividadeDiagnostica.service;

import com.vinicius.AtividadeDiagnostica.entity.Ponto;
import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.mapper.PontoMapper;
import com.vinicius.AtividadeDiagnostica.model.RelatorioUsuario;
import com.vinicius.AtividadeDiagnostica.repository.PontoBanco;
import com.vinicius.AtividadeDiagnostica.repository.UsuarioBanco;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PontoServiceTest {

    @InjectMocks
    private PontoService service;

    @Mock
    private UsuarioBanco usuarioBanco;
    @Mock
    private PontoBanco banco;
    @Mock
    private PontoMapper mapper;
    private Ponto ponto1;
    private Ponto ponto2;
    private Usuario user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ponto1 = new Ponto();
        ponto1.setHorario(LocalDateTime.now());
        ponto1.setTipoBatida("ENTRADA");

        ponto2 = new Ponto();
        ponto2.setHorario(LocalDateTime.now());
        ponto2.setTipoBatida("SAIDA");

        user = new Usuario();
        user.setNomeCompleto("vinicius Venditti");
        user.setCpf("97646134911");
        user.setEmail("vini@teste.com");
        user.setDataCadastro("27/07/2020");
    }

    @Test
    public void deveSalvarPonto() throws Exception {
        Mockito.when(banco.save(Mockito.any(Ponto.class))).thenReturn(new Ponto());
        Ponto p = service.marcarPonto(ponto1);
        Assert.assertNotNull(p);
    }

    @Test
    public void deveRetornarRelatorioPorUsuario() throws Exception {
        List<Ponto> listaPontos = new ArrayList<Ponto>();
        listaPontos.add(ponto1);
        listaPontos.add(ponto2);

        Mockito.when(banco.findPontoByUsuario(1l)).thenReturn(listaPontos);
        Mockito.when(usuarioBanco.findById(1l)).thenReturn(Optional.of(user));

        RelatorioUsuario relatorio = service.obterRelatorioUsuario(1l);

        Assert.assertNotNull(relatorio);
    }

}

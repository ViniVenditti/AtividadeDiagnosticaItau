package com.vinicius.AtividadeDiagnostica.controller;

import com.vinicius.AtividadeDiagnostica.entity.Ponto;
import com.vinicius.AtividadeDiagnostica.mapper.PontoMapper;
import com.vinicius.AtividadeDiagnostica.model.PontoModel;
import com.vinicius.AtividadeDiagnostica.model.RelatorioUsuario;
import com.vinicius.AtividadeDiagnostica.service.PontoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/ponto")
public class PontoController {

    private PontoService service;
    private PontoMapper mapper;

    private PontoController(PontoService service, PontoMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity<RelatorioUsuario> listarPontosPorUsuario(@PathVariable Long idUsuario) {
        RelatorioUsuario relatorioUsuario = this.service.obterRelatorioUsuario(idUsuario);

        return ResponseEntity.ok(relatorioUsuario);
    }

    @PostMapping
    public ResponseEntity<Ponto> baterPonto(@RequestBody PontoModel pontoModel) {
        Ponto marcacao = this.mapper.from(pontoModel);
        Ponto pontoMarcado = this.service.marcarPonto(marcacao);

        return ResponseEntity.ok(pontoMarcado);
    }


}

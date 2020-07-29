package com.vinicius.AtividadeDiagnostica.service;

import com.vinicius.AtividadeDiagnostica.entity.Ponto;
import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import com.vinicius.AtividadeDiagnostica.model.RelatorioUsuario;
import com.vinicius.AtividadeDiagnostica.repository.PontoBanco;
import com.vinicius.AtividadeDiagnostica.repository.UsuarioBanco;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PontoService {

    private PontoBanco pontoBanco;
    private UsuarioBanco usuarioBanco;

    public PontoService(PontoBanco pontoBanco, UsuarioBanco usuarioBanco){
        this.pontoBanco = pontoBanco;
        this.usuarioBanco = usuarioBanco;
    }

    public Ponto marcarPonto(Ponto ponto) {
        return this.pontoBanco.save(ponto);
    }

    public RelatorioUsuario obterRelatorioUsuario(Long idUsuario) {
        List<Ponto> listaPontos = this.pontoBanco.findPontoByUsuario(idUsuario);
        Optional<Usuario> user = this.usuarioBanco.findById(idUsuario);
        RelatorioUsuario relatorio = new RelatorioUsuario();
        List<Long> horasTrabalhadasPorDia = new ArrayList<Long>();

        Map<LocalDateTime, List<Ponto>> mapPontoDia = listaPontos
                .stream()
                .collect(Collectors.groupingBy(ponto -> ponto.getHorario().truncatedTo(ChronoUnit.DAYS)));

        mapPontoDia.entrySet().stream().forEach(entrada -> {
            List<Ponto> pontoEntrada = entrada.getValue();
            Map<String, List<Ponto>> collect = pontoEntrada.stream().collect(Collectors.groupingBy(p -> p.getTipoBatida()));
            Ponto entradas = collect.get("ENTRADA").get(0);
            Ponto saidas = collect.get("SAIDA").get(0);
            horasTrabalhadasPorDia.add(entradas.getHorario().until(saidas.getHorario(), ChronoUnit.HOURS));
        });

        relatorio.setTotalHoras(horasTrabalhadasPorDia.stream().reduce(0l, Long::sum));
        relatorio.setUsuario(user.get());
        return relatorio;
    }

}

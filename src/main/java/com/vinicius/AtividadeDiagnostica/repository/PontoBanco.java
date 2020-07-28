package com.vinicius.AtividadeDiagnostica.repository;

import com.vinicius.AtividadeDiagnostica.entity.Ponto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PontoBanco extends JpaRepository<Ponto, Long> {

    @Query("select p from Ponto p inner join p.usuario u where u.id = :usuarioId ")
    List<Ponto> findPontoByUsuario(Long usuarioId);
}

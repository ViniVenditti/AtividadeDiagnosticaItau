package com.vinicius.AtividadeDiagnostica.repository;

import com.vinicius.AtividadeDiagnostica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioBanco extends JpaRepository<Usuario, Long> {


}

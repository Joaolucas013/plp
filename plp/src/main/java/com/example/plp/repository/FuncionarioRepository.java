package com.example.plp.repository;

import com.example.plp.model.Cliente;
import com.example.plp.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}

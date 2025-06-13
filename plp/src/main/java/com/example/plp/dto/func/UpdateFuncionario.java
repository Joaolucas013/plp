package com.example.plp.dto.func;

import jakarta.persistence.Id;

import java.time.LocalDate;

public record UpdateFuncionario(

        @Id
        Long id,
        String nome,
        String cpf,
        LocalDate dataAdmissao,
        LocalDate dataDemissao,
        Double salario
) {
}

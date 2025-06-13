package com.example.plp.dto.func;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FuncionarioDto(

        @NotNull
        String nome,
        @NotNull
        String cpf,
        LocalDate dataAdmissao,
        LocalDate dataDemissao,
        double salario
)

{
}

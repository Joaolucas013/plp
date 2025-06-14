package com.example.plp.dto.func;

import com.example.plp.model.Funcionario;

import java.time.LocalDate;

public record ListarFuncionario(

        Long id,
        String nome,
        String cpf,
        LocalDate dataAdmissao,
        double salario
) {

    public ListarFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(), funcionario.getDataAdmissao(),
                funcionario.getSalario());
    }


}

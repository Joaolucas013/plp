package com.example.plp.model;


import com.example.plp.dto.func.UpdateFuncionario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table( name = "funcionarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Funcionario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private LocalDate dataAdmissao;
    @Column
    private LocalDate dataDemissao;
    @Column
    private double salario;

    @Column
    private boolean ativo;

    @OneToMany(mappedBy = "funcionario")
    private List<Pedido> pedido;


    public Funcionario update(@Valid UpdateFuncionario dto) {
        var funcionario = new Funcionario();
        if(dto.cpf()!=null){
            funcionario.setCpf(dto.cpf());
        }
        if(dto.dataAdmissao()!=null){
            funcionario.setDataAdmissao(dto.dataAdmissao());
        }
        if(dto.nome()!=null){
            funcionario.setNome(dto.nome());
        }
        if(dto.salario()!=null){
            funcionario.setSalario(dto.salario());
        }
        return  funcionario;
    }


}

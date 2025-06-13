package com.example.plp.model;


import com.example.plp.dto.func.UpdateFuncionario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table( name = "funcionarios")
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(mappedBy = "funcionario")
    private List<Pedido> pedido;


    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public LocalDate getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(LocalDate dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

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

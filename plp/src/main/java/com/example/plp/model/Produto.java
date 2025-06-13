package com.example.plp.model;

import com.example.plp.dto.prod.ProdutoDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Entity
@Table( name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduto;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private int quantidade;

    @Column
    private double preco;

    @OneToMany(mappedBy = "produtos")
    private List<Itens> itens;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public List<Itens> getItens() {
        return itens;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setItens(List<Itens> itens) {
        this.itens = itens;
    }


    public Produto cadastrar(@Valid @RequestBody ProdutoDto dto) {
        var produto = new Produto();
        this.setNome(dto.nome());
        this.setPreco(dto.preco());
        this.setQuantidade(dto.quantidade());
        this.setDescricao(dto.descricao());
        return produto;
    }
}

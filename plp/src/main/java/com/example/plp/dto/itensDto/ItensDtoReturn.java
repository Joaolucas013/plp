package com.example.plp.dto.itensDto;

import com.example.plp.model.Itens;
import com.fasterxml.jackson.annotation.JsonProperty;


public record ItensDtoReturn(
        int quantidade, String nome, String descricao, Double preco
) {


    public ItensDtoReturn(Itens listarItens) {
        this(listarItens.getProdutos().getQuantidade(), listarItens.getProdutos().getNome(),
                listarItens.getProdutos().getDescricao(), listarItens.getProdutos().getPreco());
    }

    @Override
    public String toString() {
        return "ItensDtoReturn{" +
                "quantidade=" + quantidade +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                '}';
    }
}

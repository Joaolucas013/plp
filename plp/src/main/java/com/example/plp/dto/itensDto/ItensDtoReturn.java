package com.example.plp.dto.itensDto;

import com.example.plp.model.Itens;

public record ItensDtoReturn(
        int quantidade, String nome, String descricao, Double preco
) {


    public ItensDtoReturn(Itens listarItens) {
        this(listarItens.getProdutos().getQuantidade(), listarItens.getProdutos().getNome(),
                listarItens.getProdutos().getDescricao(), listarItens.getProdutos().getPreco());
    }
}

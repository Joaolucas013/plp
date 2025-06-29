package com.example.plp.dto.prod;

import com.example.plp.model.Produto;

public record ListarProduto(
        String nome,
        Long idProduto,
        String descricao,
        int quantidade,
        Double preco
) {
    public ListarProduto(Produto produto){
        this(produto.getNome(), produto.getIdProduto(), produto.getDescricao(), produto.getQuantidade(), produto.getPreco());
    }
}

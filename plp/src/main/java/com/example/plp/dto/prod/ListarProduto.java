package com.example.plp.dto.prod;

import com.example.plp.model.Produto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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

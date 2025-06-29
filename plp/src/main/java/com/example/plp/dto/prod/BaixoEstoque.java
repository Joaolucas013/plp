package com.example.plp.dto.prod;

import com.example.plp.model.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BaixoEstoque(
           @JsonProperty("AVISO!")
            String msg,
            String nome,
            Long idProduto,
           @JsonProperty("Quantidade em estoque")
           int quantidade) {


    public BaixoEstoque(String msg, Produto produto){
        this(msg,produto.getNome(), produto.getIdProduto(), produto.getQuantidade());
    }
}

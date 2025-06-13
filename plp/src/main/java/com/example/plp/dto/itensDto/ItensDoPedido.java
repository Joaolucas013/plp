package com.example.plp.dto.itensDto;

import com.example.plp.model.Itens;

public record ItensDoPedido(
        Long idItem,
        int quantidade,
        Long produtoId,
        Long pedidoId

) {
    public  ItensDoPedido(Itens itens){
        this(itens.getIdItem(),itens.getQuantidade(),itens.getProdutos().getIdProduto(), itens.getPedido().getId() );
    }
}

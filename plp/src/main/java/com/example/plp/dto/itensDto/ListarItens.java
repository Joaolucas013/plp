package com.example.plp.dto.itensDto;

import com.example.plp.model.Itens;

public record ListarItens(
  String produto,
  String clienteName,
  String vendendor,
  int quantidade
) {

    public ListarItens(Itens itens){
        this(itens.getProdutos().getNome(), itens.getPedido().getCliente().getNome(),
                itens.getPedido().getFuncionario().getNome(), itens.getQuantidade());
    }
}

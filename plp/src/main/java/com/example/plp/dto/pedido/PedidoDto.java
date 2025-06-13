package com.example.plp.dto.pedido;

import com.example.plp.model.Itens;
import com.example.plp.model.Pedido;

import java.util.List;

public record PedidoDto(
        Long idFuncionario,
        Long idCliente,
        List<Itens> itens
) {

    public PedidoDto(Pedido pedido){
        this(pedido.getFuncionario().getId(), pedido.getCliente().getId(), pedido.getItens());
    }
}


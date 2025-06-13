package com.example.plp.dto.pedido;

import com.example.plp.model.Cliente;
import com.example.plp.model.Itens;
import com.example.plp.model.Pedido;

import java.util.List;

public record ListarPedido(
        Long idPedido,
        Long idFuncionario,
        String  nomeCliente
       // List<Itens> itens
) {

    public ListarPedido(Pedido pedido){
        this(pedido.getId(), pedido.getFuncionario().getId(), pedido.getCliente().getNome());
    }

}

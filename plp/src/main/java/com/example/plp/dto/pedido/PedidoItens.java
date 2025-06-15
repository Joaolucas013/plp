package com.example.plp.dto.pedido;

public record PedidoItens(
        int quantidade,
        Long produtoId,
        Long idFuncionario,
        Long idCliente
) {
}

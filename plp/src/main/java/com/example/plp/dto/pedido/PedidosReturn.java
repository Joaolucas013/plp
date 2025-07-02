package com.example.plp.dto.pedido;

public record PedidosReturn(Long pedidoId,
                            Long funcionarioId,
                            String funcionario, String cliente, int quantidade,
                            String produtoNome,
                            Double preco)  {
}

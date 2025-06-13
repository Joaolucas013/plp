package com.example.plp.dto.itensDto;

public record PostItem(
        int quantidade,
        Long produtoId,
        Long pedidoId
) {
}

package com.example.plp.dto.prod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoDto(
        Long id,
        @NotNull
        String nome,
        @NotNull
        String descricao,

        @Positive
        int quantidade,

        @Positive
        Double preco
) {
}

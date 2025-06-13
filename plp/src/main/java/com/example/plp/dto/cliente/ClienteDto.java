package com.example.plp.dto.cliente;

import jakarta.validation.constraints.NotNull;

public record ClienteDto(
        @NotNull
        String nome,
        String cpf

) {
}

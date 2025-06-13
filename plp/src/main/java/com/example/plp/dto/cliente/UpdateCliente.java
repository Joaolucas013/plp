package com.example.plp.dto.cliente;

import jakarta.persistence.Id;

public record UpdateCliente(
        String nome,
        String cpf,
        @Id
        Long id
) {
}

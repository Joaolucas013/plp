package com.example.plp.dto.itensDto;

import com.example.plp.model.Itens;

import java.util.List;

public record PostItem(
        int quantidade,
        Long produtoId,
        Long idFuncionario,
        Long idCliente
) {
}

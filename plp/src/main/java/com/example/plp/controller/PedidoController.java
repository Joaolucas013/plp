package com.example.plp.controller;

import com.example.plp.dto.pedido.ListarPedido;
import com.example.plp.dto.pedido.PedidoDto;
import com.example.plp.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pedidos")
public class PedidoController {


    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/realizar")
    public ResponseEntity<ListarPedido> fazerPedido(@RequestBody @Valid PedidoDto pedidoDto){
        var pedido = pedidoService.cadastrar(pedidoDto);

        return ResponseEntity.ok(pedido);
    }


}



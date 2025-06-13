package com.example.plp.controller;

import com.example.plp.dto.pedido.ListarPedido;
import com.example.plp.dto.pedido.PedidoDto;
import com.example.plp.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/listar")
    public ResponseEntity<Page<ListarPedido>> listar(@PageableDefault(size = 20) Pageable pageable){
        var page =  pedidoService.listarPedidos(pageable);

        return ResponseEntity.ok(page);
    }

}



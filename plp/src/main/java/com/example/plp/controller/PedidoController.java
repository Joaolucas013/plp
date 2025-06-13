package com.example.plp.controller;

import com.example.plp.dto.pedido.ListarPedidos;
import com.example.plp.service.PedidoService;
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


    @GetMapping("/listar")
    public ResponseEntity<Page<ListarPedidos>> listar(@PageableDefault(size = 5) Pageable pageable){
        var page =  pedidoService.listarPedidos(pageable);

        return ResponseEntity.ok(page);
    }

}



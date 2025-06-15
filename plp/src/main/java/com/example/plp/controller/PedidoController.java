package com.example.plp.controller;

import com.example.plp.dados.DadosCompras;
import com.example.plp.dto.pedido.ListarPedidos;
import com.example.plp.dto.pedido.PedidoItens;
import com.example.plp.dto.pedido.PedidosReturn;
import com.example.plp.service.PedidoService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @PostMapping("/criar")
    public ResponseEntity<PedidosReturn> criar(@RequestBody @Valid PedidoItens itens){
        var pedidos = pedidoService.cadastrarPedido(itens);

        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ListarPedidos>> listar(@PageableDefault(size = 5) Pageable pageable){
        var page =  pedidoService.listarPedidos(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/cliente_fiel")
    public ResponseEntity<DadosCompras> dados(){
        var cliente = pedidoService.clienteFiel();

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/delete/{idPedido}")
    @Transactional
    public ResponseEntity deletar (@PathVariable Long idPedido){
         pedidoService.deletar(idPedido);
        return ResponseEntity.noContent().build();
    }

}



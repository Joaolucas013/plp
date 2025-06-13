package com.example.plp.controller;


import com.example.plp.dto.itensDto.ItensDoPedido;
import com.example.plp.dto.itensDto.ListarItens;
import com.example.plp.dto.itensDto.PostItem;
import com.example.plp.service.ItensService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("itens")
public class ItensController {

    @Autowired
    private ItensService service;

    @PostMapping("post")
    public ResponseEntity<ItensDoPedido> cadastrarPedido(@Valid @RequestBody PostItem itens){
        var item = service.cadastrar(itens);

        return ResponseEntity.ok(item);
    }


    @GetMapping("/listar")
    public ResponseEntity<Page<ListarItens>> listarItens(@PageableDefault(size = 12)Pageable pageable){
        var itens = service.paginacao(pageable);
        return ResponseEntity.ok(itens);
    }
}

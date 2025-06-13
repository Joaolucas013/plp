package com.example.plp.controller;

import com.example.plp.dto.Mensagem;
import com.example.plp.dto.prod.*;
import com.example.plp.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.OnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
public class ProdutoController{


    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/post")
    @Transactional
    public ResponseEntity<ListarProduto> criarProduto(@RequestBody @Valid ProdutoDto dto){
        var prod = produtoService.cadastrar(dto);

        return ResponseEntity.ok(prod);
    }

    @GetMapping("/read")
    public ResponseEntity<Page<ListarProduto>> listar(@PageableDefault(sort = {"nome"}, size = 15) Pageable pageable){
        var page = produtoService.paginacao(pageable);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        produtoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/clube")
    public ResponseEntity<List<ListarClube>> buscarProdutoPorClube(@RequestBody @Valid ProdutoEspecifico  dto){
        var club = produtoService.buscar(dto);
        return ResponseEntity.ok(club);
    }


    @GetMapping("/clube_desconto")
    public ResponseEntity<List<ListarClube>> descontoEmProduto(@RequestBody @Valid ProdutoEspecifico  dto){
        var club = produtoService.descontoEmProduto(dto);
        return ResponseEntity.ok(club);
    }

    @GetMapping("/total_estoque")
    public ResponseEntity<List<Mensagem>> estoque(){
        List<Mensagem> mensagems = produtoService.total();
        return ResponseEntity.ok(mensagems);
    }



}

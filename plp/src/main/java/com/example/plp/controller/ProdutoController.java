package com.example.plp.controller;

import com.example.plp.dto.Mensagem;
import com.example.plp.dto.prod.*;
import com.example.plp.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public ResponseEntity<ListarProduto> criarProduto(@RequestBody @Valid ProdutoDto dto) {
        var prod = produtoService.cadastrar(dto);

        return ResponseEntity.ok(prod);
    }

    @GetMapping("/read")
    public ResponseEntity<Page<ListarProduto>> listar(@PageableDefault(sort = {"idProduto"}, size = 15) Pageable pageable) {
        var page = produtoService.paginacao(pageable);

        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        produtoService.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/produto")
    public ResponseEntity<List<ListarClube>> buscarProdutoEspecifico(@RequestBody @Valid ProdutoEspecifico produtoEspecifico) {
        var club = produtoService.buscar(produtoEspecifico);
        return ResponseEntity.ok(club);
    }


    @GetMapping("/desconto")
    public ResponseEntity<List<ListarClube>> descontoEmProduto(@RequestBody @Valid ProdutoEspecifico dto) {
        var club = produtoService.descontoEmProduto(dto);
        return ResponseEntity.ok(club);
    }

    @GetMapping("/total_estoque")
    public ResponseEntity<List<Mensagem>> estoque() {
        List<Mensagem> mensagens = produtoService.total();
        return ResponseEntity.ok(mensagens);
    }

    @Transactional
    @GetMapping("/update")
    public ResponseEntity<ProdutoDto> up(@RequestBody @Valid ListarProduto atualizar) {
        var prodAtualizado = produtoService.atualizar(atualizar);

        return ResponseEntity.ok(prodAtualizado);
    }

    @GetMapping("/baixo_estoque")
    public ResponseEntity<Page<BaixoEstoque>> baixoEstoque(@PageableDefault(size = 10) Pageable pageable) {
        var page = produtoService.listar(pageable);
        return ResponseEntity.ok(page);
    }


}

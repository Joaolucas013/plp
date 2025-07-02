package com.example.plp.controller;


import com.example.plp.dto.cliente.ClienteDto;
import com.example.plp.dto.cliente.UpdateCliente;
import com.example.plp.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;

    @PostMapping("/post")
    public ResponseEntity<UpdateCliente> cadastrarCliente(@RequestBody @Valid ClienteDto dto) {
        var cliente = clienteService.cadastrar(dto);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("up/{id}")
    public ResponseEntity<UpdateCliente> update(@PathVariable Long id, @RequestBody @Valid ClienteDto dto){
        var clienteUp = clienteService.updateCliente(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(clienteUp);
    }

    @GetMapping("/read")
    public ResponseEntity<Page<UpdateCliente>> listar(Pageable pageable){
        var page = clienteService.paginacao(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.ok("Cliente:" + id + " deletado com sucesso!");
    }

    @GetMapping("buscar/{nome}")
    public ResponseEntity<List<UpdateCliente>> buscarClientePorNome(@PathVariable String nome) {
        var cliente = clienteService.buscar(nome);
        return ResponseEntity.ok(cliente);

    }


}

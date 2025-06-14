package com.example.plp.controller;

import com.example.plp.dto.func.FuncionarioDto;
import com.example.plp.dto.func.ListarFuncionario;
import com.example.plp.dto.func.UpdateFuncionario;
import com.example.plp.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {


    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/post")
    public ResponseEntity<ListarFuncionario> cadastrarFuncionario(@RequestBody @Valid FuncionarioDto dto) {
        var funcionario = funcionarioService.cadastrar(dto);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping("read")
    public ResponseEntity<Page<ListarFuncionario>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        var page = funcionarioService.listarFuncionariosAtuais(pageable);
        return ResponseEntity.ok(page);

    }

    public ResponseEntity<ListarFuncionario> update(@RequestBody @Valid UpdateFuncionario dto){
        var up = funcionarioService.update(dto);
        return ResponseEntity.ok(up);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id){
        funcionarioService.delete(id);

        return ResponseEntity.ok("Funcionario " + id + " deletado com sucesso!!!");
    }

}

package com.example.plp.repository;

import com.example.plp.dados.FuncionarioFiel;
import com.example.plp.dto.func.ListarFuncionario;
import com.example.plp.model.Cliente;
import com.example.plp.model.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Page<ListarFuncionario> findAllByAtivoTrue(Pageable pageable);


    @Query(value = "select count(p.funcionario_id) qtd, f.nome from pedidos p\n" +
            "join funcionarios f on p.funcionario_id = f.id\n" +
            "group by funcionario_id, f.nome\n" +
            "order by qtd desc\n" +
            "limit 1", nativeQuery = true)
    FuncionarioFiel vendas();
}

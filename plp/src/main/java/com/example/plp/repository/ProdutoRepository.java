package com.example.plp.repository;

import com.example.plp.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    @Query("SELECT p FROM Produto p where p.nome LIKE %:clube%")
    List<Produto> buscarPorClube(@Param("clube") String clube);
}



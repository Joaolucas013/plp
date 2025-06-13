package com.example.plp.repository;

import com.example.plp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query("SELECT c FROM Cliente c where c.nome Like %:nome%")
    List<Cliente> buscar(@Param("nome") String nome);


}

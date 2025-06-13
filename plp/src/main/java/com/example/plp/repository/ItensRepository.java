package com.example.plp.repository;

import com.example.plp.model.Cliente;
import com.example.plp.model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensRepository extends JpaRepository<Itens, Long> {
}

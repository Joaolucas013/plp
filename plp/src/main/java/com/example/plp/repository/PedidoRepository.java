package com.example.plp.repository;

import com.example.plp.model.Cliente;
import com.example.plp.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

package com.example.plp.repository;

import com.example.plp.dados.DadosCompras;
import com.example.plp.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query(value = "select count(cliente_id) qtd_de_pedidos,  c.nome from pedidos p\n" +
            "join clientes c on p.cliente_id = c.id\n" +
            "group by cliente_id, c.nome\n" +
            "limit 1", nativeQuery = true)
    DadosCompras dados ();
}

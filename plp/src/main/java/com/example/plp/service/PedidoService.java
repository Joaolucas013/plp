package com.example.plp.service;


import com.example.plp.dto.itensDto.PostItem;
import com.example.plp.dto.pedido.ListarPedido;
import com.example.plp.dto.pedido.PedidoDto;
import com.example.plp.model.Itens;
import com.example.plp.model.Pedido;
import com.example.plp.repository.ClienteRepository;
import com.example.plp.repository.FuncionarioRepository;
import com.example.plp.repository.PedidoRepository;
import com.example.plp.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public Page<ListarPedido> listarPedidos(Pageable pageable) {
        return repository.findAll(pageable).map(ListarPedido::new);
    }

    public Pedido cadastrarPedido(@Valid PostItem itens) {
        var pedido = new Pedido();

        var func =  funcionarioRepository.findById(itens.idFuncionario()).orElseThrow();
        var cliente = clienteRepository.findById(itens.idCliente()).orElseThrow();
        pedido.setCliente(cliente);
        pedido.setFuncionario(func);
        pedido.setHoraPedido(LocalDateTime.now());
        return pedido;
    }
}

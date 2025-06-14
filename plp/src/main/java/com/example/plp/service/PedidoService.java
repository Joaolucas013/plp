package com.example.plp.service;


import com.example.plp.dto.itensDto.ItensDtoReturn;
import com.example.plp.dto.itensDto.PostItem;
import com.example.plp.dto.pedido.ListarPedidos;
import com.example.plp.model.Pedido;
import com.example.plp.repository.ClienteRepository;
import com.example.plp.repository.FuncionarioRepository;
import com.example.plp.repository.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public Pedido cadastrarPedido(@Valid PostItem itens) {
        var pedido = new Pedido();

        var func =  funcionarioRepository.findById(itens.idFuncionario()).orElseThrow();
        var cliente = clienteRepository.findById(itens.idCliente()).orElseThrow();
        pedido.setCliente(cliente);
        pedido.setFuncionario(func);
        pedido.setHoraPedido(LocalDateTime.now());
        return pedido;
    }

    public Page<ListarPedidos> listarPedidos(Pageable pageable) {
        return repository.findAll(pageable).map(p -> new ListarPedidos(
                p.getId(),
                p.getFuncionario().getId(),
                p.getFuncionario().getNome(),
                p.getCliente().getNome(),
                p.getItens().stream()
                        .map(i -> new ItensDtoReturn(
                                i.getQuantidade(),
                                i.getProdutos().getNome(),
                                i.getProdutos().getDescricao(),
                                i.getProdutos().getPreco()))
                        .collect(Collectors.toList())
        ));
    }




}

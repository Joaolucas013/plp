package com.example.plp.service;


import com.example.plp.dto.itensDto.ItensDtoReturn;
import com.example.plp.dto.pedido.PedidoItens;
import com.example.plp.dto.pedido.ListarPedidos;
import com.example.plp.dto.pedido.PedidosReturn;
import com.example.plp.model.Itens;
import com.example.plp.model.Pedido;
import com.example.plp.repository.*;
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

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItensRepository itensRepository;

    @Autowired
    private ItensService itensService;



    public PedidosReturn cadastrarPedido(@Valid PedidoItens itens) {
        var pedido = new Pedido();

        var func = funcionarioRepository.findById(itens.idFuncionario()).orElseThrow();
        var cliente = clienteRepository.findById(itens.idCliente()).orElseThrow();
        var produto = produtoRepository.findById(itens.produtoId()).get();

        if (produto.getQuantidade() - itens.quantidade() < 0) {
            throw new RuntimeException("Não há " + itens.quantidade() + " produtos  disponíveis!!!" + " apenas: " +
                    produto.getQuantidade());
        }

        produto.setQuantidade(produto.getQuantidade() - itens.quantidade());
        pedido.setCliente(cliente);
        pedido.setFuncionario(func);
        pedido.setHoraPedido(LocalDateTime.now());
        produtoRepository.save(produto);
        repository.save(pedido);

        var itensSalvos = itensService.cadastrar(produto, pedido, itens.quantidade());
        var itemDto = new ItensDtoReturn(itensSalvos.getQuantidade(), itensSalvos.getProdutos().getNome(),
                itensSalvos.getProdutos().getDescricao(), itensSalvos.getProdutos().getPreco());


        return new PedidosReturn(pedido.getId(), func.getId(), func.getNome(), cliente.getNome(),
                itemDto.quantidade(), itemDto.nome(), itemDto.preco());
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

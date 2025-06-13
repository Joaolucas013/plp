package com.example.plp.service;


import com.example.plp.dto.pedido.ListarPedido;
import com.example.plp.dto.pedido.PedidoDto;
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

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

//    @Autowired
//    private ProdutoRepository produtoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public ListarPedido cadastrar(PedidoDto pedidoDto) {
        var pedido = new Pedido();

        var func =  funcionarioRepository.findById(pedidoDto.idFuncionario()).orElseThrow();
        var cliente = clienteRepository.findById(pedidoDto.idCliente()).orElseThrow();

        pedido.setCliente(cliente);
        pedido.setFuncionario(func);
        repository.save(pedido);

        return new ListarPedido(pedido.getId(), pedido.getFuncionario().getId(),
                pedido.getCliente().getNome());
    }

    public Page<ListarPedido> listarPedidos(Pageable pageable) {
        return repository.findAll(pageable).map(ListarPedido::new);
    }
}

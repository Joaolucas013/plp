package com.example.plp.service;


import com.example.plp.dto.itensDto.ItensDoPedido;
import com.example.plp.dto.itensDto.ListarItens;
import com.example.plp.dto.itensDto.PostItem;
import com.example.plp.model.Itens;
import com.example.plp.repository.ItensRepository;
import com.example.plp.repository.PedidoRepository;
import com.example.plp.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItensService {

    @Autowired
    private ItensRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoRepository produtoRepository;


    public ItensDoPedido cadastrar(@Valid PostItem itens) {
        var item = new Itens();
        var pedido = pedidoService.cadastrarPedido(itens);

        var produto = produtoRepository.findById(itens.produtoId()).get();
        if(produto.getQuantidade() - itens.quantidade() < 0){
            throw new RuntimeException("Não há " + itens.quantidade() + " produtos  disponíveis!!!");
        }

        produto.setQuantidade(produto.getQuantidade() - itens.quantidade());
        produtoRepository.save(produto);
        pedidoRepository.save(pedido);

        item.setPedido(pedido);
        item.setProdutos(produto);
        item.setQuantidade(itens.quantidade());
        repository.save(item);

        return new ItensDoPedido(item.getIdItem(), item.getQuantidade(), item.getProdutos().getIdProduto(),
                item.getPedido().getId());
    }

    public Page<ListarItens> paginacao(Pageable pageable) {
        var page = repository.findAll(pageable)
                .map(i -> new ListarItens(i.getProdutos().getNome(), i.getPedido().getCliente().getNome(),
                        i.getPedido().getFuncionario().getNome(), i.getQuantidade()));
        return page;
    }
}

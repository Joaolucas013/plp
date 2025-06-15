package com.example.plp.service;


import com.example.plp.dto.itensDto.ItensDoPedido;
import com.example.plp.dto.itensDto.ListarItens;
import com.example.plp.model.Itens;
import com.example.plp.model.Pedido;
import com.example.plp.model.Produto;
import com.example.plp.repository.ItensRepository;
import com.example.plp.repository.PedidoRepository;
import com.example.plp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItensService {

    @Autowired
    private ItensRepository repository;


    public Itens cadastrar(Produto produto, Pedido pedido, int quantidade) {
        var item = new Itens();
        item.setPedido(pedido);
        item.setProdutos(produto);
        item.setQuantidade(quantidade);
        repository.save(item);

        return item;
    }

    public Page<ListarItens> paginacao(Pageable pageable) {
        var page = repository.findAll(pageable)
                .map(i -> new ListarItens(i.getProdutos().getNome(), i.getPedido().getCliente().getNome(),
                        i.getPedido().getFuncionario().getNome(), i.getQuantidade()));
        return page;
    }
}

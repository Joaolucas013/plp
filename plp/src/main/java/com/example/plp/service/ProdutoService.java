package com.example.plp.service;


import com.example.plp.dto.Mensagem;
import com.example.plp.dto.pedido.ListarPedidos;
import com.example.plp.dto.prod.*;
import com.example.plp.model.Produto;
import com.example.plp.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;


    @Transactional
    public ListarProduto cadastrar(ProdutoDto dto) {
        var prod = new Produto();
        prod.cadastrar(dto);
        repository.save(prod);

        return new  ListarProduto(prod.getNome(), prod.getIdProduto(),  prod.getDescricao(),
                prod.getQuantidade(), prod.getPreco());

    }

    public Page<ListarProduto> paginacao(Pageable pageable) {
       return repository.findAll(pageable).map(p -> new ListarProduto(p.getNome(),p.getIdProduto(), p.getDescricao(),
               p.getQuantidade(), p.getPreco()));
    }

    public void deletar(Long id) {
        Optional<Produto> prod = repository.findById(id);

        if (!prod.isPresent()){
            throw  new RuntimeException("ID DO PRODUTO NÃO ENCONTRADO!!!");
        }
        repository.deleteById(prod.get().getIdProduto());
    }

    public List<ListarClube> buscar(@Valid ProdutoEspecifico dto) {
        var produto = new Produto();

        List<Produto> time = repository.buscarPorClube(dto.clube());

        System.out.println(produto.getPreco());
      return   time.stream().map(t -> new ListarClube(t.getNome(), t.getDescricao(),
                t.getQuantidade(), t.getPreco()))
               .collect(Collectors.toList());

    }


    //metodo a ser falado em video
    public  List<ListarClube>  descontoEmProduto(@Valid ProdutoEspecifico dto) {
           Double taxa = 0.1;

            List<Produto> time = repository.buscarPorClube(dto.clube());
            List<ListarClube> clubeList  = new ArrayList<>();

            for (Produto p : time) {
                p.setPreco(p.getPreco() - p.getPreco() * taxa);

                clubeList.add(new ListarClube(p.getNome(), p.getDescricao(),
                p.getQuantidade(), p.getPreco()));

            }

            return clubeList;
    }


    // metodo a ser falado em video
    public List<Mensagem> total() {
        String msg = "valor total do produto  em estoque";
        Double total = 0.0;

        List<Produto> produtos = repository.findAll();
        List<Mensagem> totalProdutos = new ArrayList<>();

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            total = produto.getQuantidade() * produto.getPreco();
            totalProdutos.add(new Mensagem(msg, new TotalProdutos(produto.getNome(), total)));
        }

        return totalProdutos;

    }
}

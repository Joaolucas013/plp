package com.example.plp.model;

import com.example.plp.dto.prod.ProdutoDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Entity
@Table( name = "produtos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduto;

    @Column
    private String nome;

    @Column
    private String descricao;

    @Column
    private int quantidade;

    @Column
    private double preco;

    @OneToMany(mappedBy = "produtos")
    private List<Itens> itens;



    public Produto cadastrar(@Valid @RequestBody ProdutoDto dto) {
        var produto = new Produto();
        this.setNome(dto.nome());
        this.setPreco(dto.preco());
        this.setQuantidade(dto.quantidade());
        this.setDescricao(dto.descricao());
        return produto;
    }
}

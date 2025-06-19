package com.example.plp.model;

import com.example.plp.dto.pedido.PedidoDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "funcionario_id")
    private  Funcionario funcionario;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column
    private LocalDateTime horaPedido;


    @OneToMany(mappedBy = "pedido", cascade = CascadeType.REMOVE)
    private List<Itens> itens = new ArrayList<>();

    public Pedido(Funcionario funcionario, List<Itens> itens, Cliente cliente, LocalDateTime dataVenda){
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.itens = itens;
        this.horaPedido = LocalDateTime.now();
    }

}



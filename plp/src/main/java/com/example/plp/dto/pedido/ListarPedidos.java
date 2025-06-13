package com.example.plp.dto.pedido;

import com.example.plp.dto.itensDto.ItensDtoReturn;
import com.example.plp.model.Itens;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListarPedidos {

    private Long idPedido;
    private Long idFuncionario;
    private String nomeFuncionario;
    private String nomeCliente;
    private List<ItensDtoReturn> itens;


    public ListarPedidos(Long id, Long funcionarioId, String funcionarioNome,
                         String clienteNome, List<ItensDtoReturn> itens) {
        this.idPedido = id;
        this.idFuncionario = funcionarioId;
        this.nomeFuncionario = funcionarioNome;
        this.nomeCliente = clienteNome;
        this.itens = itens;
    }




    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public List<ItensDtoReturn> getItens() {
        return itens;
    }

    public void setItens(List<ItensDtoReturn> itens) {
        this.itens = itens;
    }
}

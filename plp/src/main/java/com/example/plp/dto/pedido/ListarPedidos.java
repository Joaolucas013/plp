package com.example.plp.dto.pedido;

import com.example.plp.dto.itensDto.ItensDtoReturn;
import com.example.plp.model.Itens;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class ListarPedidos {

    private Long idPedido;
    private Long idFuncionario;
    private String nomeFuncionario;
    private String nomeCliente;
    private List<ItensDtoReturn> itensDtoReturnList;


    public ListarPedidos(Long id, Long funcionarioId, String funcionarioNome,
                         String clienteNome, List<ItensDtoReturn> itens) {
        this.idPedido = id;
        this.idFuncionario = funcionarioId;
        this.nomeFuncionario = funcionarioNome;
        this.nomeCliente = clienteNome;
        this.itensDtoReturnList = itens;
    }

    public List<ItensDtoReturn> getItensDtoReturnList() {
        return itensDtoReturnList;
    }

    public void setItensDtoReturnList(List<ItensDtoReturn> itensDtoReturnList) {
        this.itensDtoReturnList = itensDtoReturnList;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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
        return itensDtoReturnList;
    }

    public void setItens(List<ItensDtoReturn> itens) {
        this.itensDtoReturnList = itens;
    }
}

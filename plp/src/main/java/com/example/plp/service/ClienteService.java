package com.example.plp.service;


import com.example.plp.dto.cliente.ClienteDto;
import com.example.plp.dto.cliente.UpdateCliente;
import com.example.plp.model.Cliente;
import com.example.plp.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository repository;

    public UpdateCliente cadastrar(@Valid ClienteDto dto) {
        var cliente = new Cliente();
        cliente.setNome(dto.nome());

        if (dto.cpf() != null) {
            cliente.setCpf(dto.cpf());
        }
        var clienteReturn = repository.save(cliente);
        return new UpdateCliente(clienteReturn.getNome(), cliente.getCpf(), cliente.getId());

    }

    public UpdateCliente updateCliente(Long id, ClienteDto dto) {
        Optional<Cliente> clienteAtualizado = repository.findById(id);

        if (!clienteAtualizado.isPresent()) {
            throw new RuntimeException("Cliente não existe!");
        }
        if (dto.cpf() != null) {
            clienteAtualizado.ifPresent(c -> c.setCpf(dto.cpf()));
        }
        if (dto.nome() != null) {
            clienteAtualizado.ifPresent(c -> c.setNome(dto.nome()));
        }
        var cliente = repository.save(clienteAtualizado.get());
        return new UpdateCliente(cliente.getNome(), cliente.getCpf(), cliente.getId());


    }

    public Page<UpdateCliente> paginacao(Pageable pageable) {
        var page = repository.findAll(pageable)
                .map(c -> new UpdateCliente(c.getNome(), c.getCpf(), c.getId()));
        return page;
    }

    public void deletar(Long id) {
        Optional<Cliente> delete = repository.findById(id);

        if (!delete.isPresent()) {
            throw new RuntimeException("id do Cliente não encontrado!");
        }
        repository.deleteById(delete.get().getId());
    }

    public List<UpdateCliente> buscar(String nome) {
        List<Cliente> clientExists = repository.buscar(nome);
        List<UpdateCliente> clientes = new ArrayList<>();

        while (!clientExists.isEmpty()) {
            Cliente cliente = clientExists.removeFirst();
            clientes.add(new UpdateCliente(cliente.getNome(), cliente.getCpf(), cliente.getId()));
        }
        System.out.println(nome);
        System.out.println(clientes);
        return clientes;
    }


}

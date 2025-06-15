package com.example.plp.service;

import com.example.plp.dados.FuncionarioFiel;
import com.example.plp.dto.func.FuncionarioDto;
import com.example.plp.dto.func.ListarFuncionario;
import com.example.plp.dto.func.UpdateFuncionario;
import com.example.plp.model.Funcionario;
import com.example.plp.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public ListarFuncionario cadastrar(@Valid FuncionarioDto dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(dto.cpf());
        funcionario.setNome(dto.nome());
        funcionario.setDataAdmissao(dto.dataAdmissao());
        funcionario.setSalario(dto.salario());
        funcionario.setAtivo(true);

        var salvo = funcionarioRepository.save(funcionario);
        return new ListarFuncionario(salvo.getId(), salvo.getNome(), salvo.getCpf(),
                salvo.getDataAdmissao(), salvo.getSalario());

    }


    public Page<ListarFuncionario>  listarFuncionariosAtuais(Pageable pageable) {
        return funcionarioRepository.findAllByAtivoTrue(pageable)
                .map(f -> new ListarFuncionario(f.id(), f.nome(), f.cpf(),
                        f.dataAdmissao(),f.salario()));


    }

    public ListarFuncionario update(@Valid UpdateFuncionario dto) {
        var funcionario = funcionarioRepository.getReferenceById(dto.id());
        funcionario.update(dto);

        funcionarioRepository.save(funcionario);

        return new ListarFuncionario(funcionario.getId(), funcionario.getNome(), funcionario.getCpf(),
                funcionario.getDataAdmissao(), funcionario.getSalario());
    }

    public void delete(Long id) {
        Optional<Funcionario> funExist = funcionarioRepository.findById(id);

        if (!funExist.isPresent()) {
            throw new RuntimeException("Funcionário não existe no nosso banco de dados!");
        }
        funcionarioRepository.deleteById(funExist.get().getId());
    }

    public FuncionarioFiel fiel() {
        return funcionarioRepository.vendas();
    }
}

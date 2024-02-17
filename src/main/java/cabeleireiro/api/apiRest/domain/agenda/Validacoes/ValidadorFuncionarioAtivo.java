package cabeleireiro.api.apiRest.domain.agenda.Validacoes;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.DadosAgendamento;
import cabeleireiro.api.apiRest.domain.funcionario.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorFuncionarioAtivo implements ValidadorAgendamentoDeServico {

    @Autowired
    private FuncionarioRepository repository;

    public void validar(DadosAgendamento dados){

        if(dados.idFuncionario() == null){
            return;
        }

        var funcionarioEstaAtivo = repository.findAtivoById(dados.idFuncionario());

        if(!funcionarioEstaAtivo){
            throw new ValidacaoException("Serviço não poder ser agendado com Funcionário inativo");
        }

    }

}

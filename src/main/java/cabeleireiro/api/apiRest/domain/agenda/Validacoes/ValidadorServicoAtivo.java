package cabeleireiro.api.apiRest.domain.agenda.Validacoes;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.DadosAgendamento;
import cabeleireiro.api.apiRest.domain.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorServicoAtivo implements ValidadorAgendamentoDeServico {

    @Autowired
    private ServicoRepository repository;

    public void validar(DadosAgendamento dados){

        if(dados.idServico() == null){
            return;
        }

        var servicoEstaAtivo = repository.findAtivoById(dados.idServico());

        if (!servicoEstaAtivo) {
            throw new ValidacaoException("Serviço não poder ser agendado com Opção inativa");
        }

    }
}

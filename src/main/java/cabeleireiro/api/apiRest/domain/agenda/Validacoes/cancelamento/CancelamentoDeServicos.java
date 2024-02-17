package cabeleireiro.api.apiRest.domain.agenda.Validacoes.cancelamento;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.AgendaRepository;
import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosCancelamentoAgendamento;
import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosDetalhamentoAgendamentoCancelado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelamentoDeServicos {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private List<ValidadorCancelamentoDeAgendamento> validadores;

    public DadosDetalhamentoAgendamentoCancelado cancelar(DadosCancelamentoAgendamento dados) {

        if(!agendaRepository.existsById(dados.idAgendamento())){
            throw new ValidacaoException("Id do Agendamento informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var servico = agendaRepository.getReferenceById(dados.idAgendamento());
        servico.cancelar(dados.motivo());

        return new DadosDetalhamentoAgendamentoCancelado(servico);

    }

}

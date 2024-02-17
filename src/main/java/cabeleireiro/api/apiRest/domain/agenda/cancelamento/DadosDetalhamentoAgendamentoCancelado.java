package cabeleireiro.api.apiRest.domain.agenda.cancelamento;

import cabeleireiro.api.apiRest.domain.agenda.Agendamento;

public record DadosDetalhamentoAgendamentoCancelado(Long id, Long idCliente, Long idFuncionario, MotivoCancelamento motivoCancelamento) {

    public DadosDetalhamentoAgendamentoCancelado(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getServico().getId(), agendamento.getFuncionario().getId(), agendamento.getMotivoCancelamento());
    }

}

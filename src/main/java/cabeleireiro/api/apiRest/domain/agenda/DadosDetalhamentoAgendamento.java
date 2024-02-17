package cabeleireiro.api.apiRest.domain.agenda;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(
        Long id,
        Long idServico,
        Long idFuncionario,
        Long idCliente,
        Long idUsuario,
        LocalDateTime data,
        LocalDateTime termino
) {
    public DadosDetalhamentoAgendamento(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getServico().getId(), agendamento.getFuncionario().getId(), agendamento.getCliente().getId(), agendamento.getUsuario().getId(), agendamento.getData(), agendamento.getTermino());
    }
}

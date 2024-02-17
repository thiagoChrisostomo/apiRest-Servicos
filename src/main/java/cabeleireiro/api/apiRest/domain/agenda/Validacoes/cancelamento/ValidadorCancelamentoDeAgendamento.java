package cabeleireiro.api.apiRest.domain.agenda.Validacoes.cancelamento;

import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosCancelamentoAgendamento;

public interface ValidadorCancelamentoDeAgendamento {

    void validar(DadosCancelamentoAgendamento dados);

}

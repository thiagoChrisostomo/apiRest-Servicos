package cabeleireiro.api.apiRest.domain.agenda.cancelamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoAgendamento(
        @NotNull
        Long idAgendamento,

        @NotNull
        MotivoCancelamento motivo) {
}

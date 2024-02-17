package cabeleireiro.api.apiRest.domain.agenda;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamento(
        @NotNull
        Long idServico,
        @NotNull
        Long idFuncionario,
        @NotNull
        Long idCliente,
        @NotNull
        @Future
        LocalDateTime data
        ) {
}

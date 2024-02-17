package cabeleireiro.api.apiRest.domain.servico;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizarServico(
        @NotNull
        Long id,
        String servico,
        BigDecimal valor,
        Integer duracao
) {
}

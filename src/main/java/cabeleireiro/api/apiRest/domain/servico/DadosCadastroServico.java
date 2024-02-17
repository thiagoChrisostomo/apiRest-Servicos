package cabeleireiro.api.apiRest.domain.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosCadastroServico(
        @NotBlank(message = "Nome do Serviço é obrigatório")
        String servico,
        @NotNull(message = "Valor do Serviço é obrigatório")
        BigDecimal valor,
        @NotNull(message = "Duração do Serviço é obrigatório")
        Integer duracao
) {
}

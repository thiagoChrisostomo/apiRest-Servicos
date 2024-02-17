package cabeleireiro.api.apiRest.domain.funcionario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizarFuncionario(
        @NotNull
        Long id,
        String nome,
        @Pattern(regexp = "(?!(\\d)\\1{10})\\d{11}")
        String telefone
) {
}

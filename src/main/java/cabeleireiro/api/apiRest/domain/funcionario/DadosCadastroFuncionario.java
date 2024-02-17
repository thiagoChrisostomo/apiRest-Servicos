package cabeleireiro.api.apiRest.domain.funcionario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroFuncionario(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "(?!(\\d)\\1{10})\\d{11}")
        String telefone) {
}

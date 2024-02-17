package cabeleireiro.api.apiRest.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCliente(
        @NotBlank(message = "Nome é obrigatório!")
        String nome,
        @NotBlank(message = "Telefone é obrigatório!")
        @Pattern(regexp = "(?!(\\d)\\1{10})\\d{11}")
        String telefone,
        @NotBlank(message = "CPF é obrigatório!")
        @Pattern(regexp = "^\\d{11}$")
        String cpf
) {
}

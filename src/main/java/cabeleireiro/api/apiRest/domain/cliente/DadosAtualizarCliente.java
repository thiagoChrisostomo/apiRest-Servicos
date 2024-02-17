package cabeleireiro.api.apiRest.domain.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizarCliente(
        @NotNull
        Long id,
        String nome,
        @Pattern(regexp = "(?!(\\d)\\1{10})\\d{11}")
        String telefone
) {

}

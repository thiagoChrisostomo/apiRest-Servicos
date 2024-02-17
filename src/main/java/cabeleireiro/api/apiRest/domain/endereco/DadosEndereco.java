package cabeleireiro.api.apiRest.domain.endereco;

import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        @Pattern(regexp = "([A-Z]{2})")
        String uf,
        @Pattern(regexp = "([0-9]{8})")
        String cep
) {
}

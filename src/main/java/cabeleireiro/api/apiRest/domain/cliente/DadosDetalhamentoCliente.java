package cabeleireiro.api.apiRest.domain.cliente;

public record DadosDetalhamentoCliente(Long id, String nome, String telefone, String cpf, Boolean Ativo) {

    public DadosDetalhamentoCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getCpf(), cliente.getAtivo());
    }

}

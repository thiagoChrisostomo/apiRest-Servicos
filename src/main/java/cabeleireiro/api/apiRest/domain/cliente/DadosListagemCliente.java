package cabeleireiro.api.apiRest.domain.cliente;

public record DadosListagemCliente(Long id, String nome, String telefone, String cpf) {

    public DadosListagemCliente(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getCpf());
    }

}

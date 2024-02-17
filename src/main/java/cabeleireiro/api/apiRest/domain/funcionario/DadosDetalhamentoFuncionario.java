package cabeleireiro.api.apiRest.domain.funcionario;

public record DadosDetalhamentoFuncionario(Long id, String nome, String telefone, Boolean Ativo) {

    public DadosDetalhamentoFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getTelefone(), funcionario.getAtivo());
    }

}

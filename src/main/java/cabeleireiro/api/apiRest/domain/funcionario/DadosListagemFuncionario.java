package cabeleireiro.api.apiRest.domain.funcionario;

public record DadosListagemFuncionario(Long id, String nome, String telefone) {

    public DadosListagemFuncionario(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getTelefone());
    }

}

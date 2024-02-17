package cabeleireiro.api.apiRest.domain.funcionario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "funcionarios")
@Entity(name = "Funcionario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private Boolean ativo;

    public Funcionario(DadosCadastroFuncionario dados) {

        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.ativo = true;

    }

    public void atualizarInformacoes(DadosAtualizarFuncionario dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

package cabeleireiro.api.apiRest.domain.servico;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "servicos")
@Entity(name = "Servico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String servico;
    private BigDecimal valor;
    private Integer duracao;
    private Boolean ativo;

    public Servico(DadosCadastroServico dados) {

        validarDuracao(dados.duracao());

        this.servico = dados.servico();
        this.valor = dados.valor();
        this.duracao = dados.duracao();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizarServico dados) {

        validarDuracao(dados.duracao());

        if(dados.servico() != null){
            this.servico = dados.servico();
        }
        if(dados.valor() != null){
            this.valor = dados.valor();
        }
        if(dados.duracao() != null){
            this.duracao = dados.duracao();
        }

    }

    public void excluir() {
        this.ativo = false;
    }

    private void validarDuracao(Integer duracao) {

        var baseDeTempo = 15;

        if((duracao % baseDeTempo) != 0 ){
            throw new ValidacaoException("Duração não é compátivel com o horário!");
        }

    }

}

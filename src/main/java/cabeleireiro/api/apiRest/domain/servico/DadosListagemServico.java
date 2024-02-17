package cabeleireiro.api.apiRest.domain.servico;

import java.math.BigDecimal;

public record DadosListagemServico(
        Long id,
        String servico,
        BigDecimal valor,
        Integer duracao

) {
    public DadosListagemServico(Servico servico){
        this(servico.getId(), servico.getServico(), servico.getValor(), servico.getDuracao());
    }
}

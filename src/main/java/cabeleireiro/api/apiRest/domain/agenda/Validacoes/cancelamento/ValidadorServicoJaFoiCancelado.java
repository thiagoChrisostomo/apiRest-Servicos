package cabeleireiro.api.apiRest.domain.agenda.Validacoes.cancelamento;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.AgendaRepository;
import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosCancelamentoAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorServicoJaFoiCancelado implements ValidadorCancelamentoDeAgendamento {

    @Autowired
    private AgendaRepository repository;

    public void validar(DadosCancelamentoAgendamento dados){

        if(repository.existsByIdAndMotivoCancelamento(dados.idAgendamento()) != null){
            throw new ValidacaoException("Serviço já encontrasse cancelado");
        }

    }

}

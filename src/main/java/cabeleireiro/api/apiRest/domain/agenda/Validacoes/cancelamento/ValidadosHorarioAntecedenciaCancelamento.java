package cabeleireiro.api.apiRest.domain.agenda.Validacoes.cancelamento;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.AgendaRepository;
import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosCancelamentoAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadosHorarioAntecedenciaCancelamento implements ValidadorCancelamentoDeAgendamento {

    @Autowired
    private AgendaRepository repository;

    public void validar(DadosCancelamentoAgendamento dados){

        var horarioDoServico = repository.findByIdDataDoServicoMarcado(dados.idAgendamento());

        var agora = LocalDateTime.now();

        var diferencaEmHoras = Duration.between(agora, horarioDoServico).toHours();

        if(diferencaEmHoras < 1){
            throw new ValidacaoException("Serviço não pode ser cancelado por ter menos de 1 hora para o início!");
        }

    }

}

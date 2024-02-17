package cabeleireiro.api.apiRest.domain.agenda.Validacoes;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.DadosAgendamento;
import cabeleireiro.api.apiRest.domain.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoSalao implements ValidadorAgendamentoDeServico {

    @Autowired
    private ServicoRepository servicoRepository;

    public void validar(DadosAgendamento dados){

        var dataAgendamento = dados.data();

        var domingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        var antesDaAberturaDoSalao = dataAgendamento.getHour() < 8;

        var tempoDeDuracaoDoServico = servicoRepository.findByIdDuracao(dados.idServico());

        var depoisDoEncerramentoDaClinica = dataAgendamento.plusMinutes(tempoDeDuracaoDoServico).getHour() > 19;

        if(domingo || antesDaAberturaDoSalao || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Marcação fora do horário de funcionamento do salão");
        }
    }

}

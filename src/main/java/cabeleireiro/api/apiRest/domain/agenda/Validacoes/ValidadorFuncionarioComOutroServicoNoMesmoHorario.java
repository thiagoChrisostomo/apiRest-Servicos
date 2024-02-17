package cabeleireiro.api.apiRest.domain.agenda.Validacoes;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.AgendaRepository;
import cabeleireiro.api.apiRest.domain.agenda.DadosAgendamento;
import cabeleireiro.api.apiRest.domain.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorFuncionarioComOutroServicoNoMesmoHorario implements ValidadorAgendamentoDeServico {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public void validar(DadosAgendamento dados){

        var terminoDoServico = dados.data().plusMinutes(servicoRepository.findByIdDuracao(dados.idServico())+1);

        var inicioDoServico = dados.data().plusMinutes(1);

        var funcionarioPossuiOutroServicoNoMesmoHorario = agendaRepository.existeFuncionarioComServicoNoMesmoHorarioDoMarcado(dados.idFuncionario(), inicioDoServico, terminoDoServico);
//
//        var funcionarioPossuiOutroServicoNoMesmoHorario = agendaRepository.findByFuncionarioIdAndDataBetweenOrTerminoBetweenAndMotivoCancelamento(dados.idFuncionario(), inicioDoServico, terminoDoServico, inicioDoServico, terminoDoServico);

        if(funcionarioPossuiOutroServicoNoMesmoHorario.isEmpty() == false){
            throw new ValidacaoException("Já existe um serviço no horário solicitado para o Funcionário");
        }

//        if(funcionarioPossuiOutroServicoNoMesmoHorario.isEmpty() == false){
//            throw new ValidacaoException("Já existe um serviço no horário solicitado para o Funcionário");
//        }

    }

}

package cabeleireiro.api.apiRest.domain.agenda;

import cabeleireiro.api.apiRest.domain.ValidacaoException;
import cabeleireiro.api.apiRest.domain.agenda.Validacoes.ValidadorAgendamentoDeServico;
import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosCancelamentoAgendamento;
import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosDetalhamentoAgendamentoCancelado;
import cabeleireiro.api.apiRest.domain.cliente.ClienteRepository;
import cabeleireiro.api.apiRest.domain.funcionario.FuncionarioRepository;
import cabeleireiro.api.apiRest.domain.servico.ServicoRepository;
import cabeleireiro.api.apiRest.domain.usuario.Usuario;
import cabeleireiro.api.apiRest.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Agenda {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorAgendamentoDeServico> validadores;

    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados, Usuario logado){

        if(!funcionarioRepository.existsById(dados.idFuncionario())){
            throw new ValidacaoException("Id do Funcionário não existe!");
        }

        if(!servicoRepository.existsById(dados.idServico())){
            throw new ValidacaoException("Id do Serviço não existe!");
        }

        if(!clienteRepository.existsById(dados.idCliente())){
            throw new ValidacaoException("Id do Cliente não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var funcionario = funcionarioRepository.getReferenceById(dados.idFuncionario());

        var servico = servicoRepository.getReferenceById(dados.idServico());

        var cliente = clienteRepository.getReferenceById(dados.idCliente());

        var usuario = usuarioRepository.getReferenceById(logado.getId());

        var dataEHorarioInicioServico = dados.data();

        var duracaoServico = servicoRepository.findByIdDuracao(dados.idServico());

        var termino = dataEHorarioInicioServico.plusMinutes(duracaoServico);

        var agendamento = new Agendamento(null, funcionario, servico, cliente, usuario, dados.data(), termino, null);

        agendaRepository.save(agendamento);

        return new DadosDetalhamentoAgendamento(agendamento);
    }

}

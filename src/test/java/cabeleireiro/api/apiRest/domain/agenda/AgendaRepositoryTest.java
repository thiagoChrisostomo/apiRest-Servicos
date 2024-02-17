package cabeleireiro.api.apiRest.domain.agenda;

import cabeleireiro.api.apiRest.domain.cliente.Cliente;
import cabeleireiro.api.apiRest.domain.cliente.DadosCadastroCliente;
import cabeleireiro.api.apiRest.domain.funcionario.DadosCadastroFuncionario;
import cabeleireiro.api.apiRest.domain.funcionario.Funcionario;
import cabeleireiro.api.apiRest.domain.servico.DadosCadastroServico;
import cabeleireiro.api.apiRest.domain.servico.Servico;
import cabeleireiro.api.apiRest.domain.usuario.DadosAutenticacao;
import cabeleireiro.api.apiRest.domain.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AgendaRepositoryTest {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver true quando o funcionário está com horário marcado naquele periodo")
    void testarSeFuncionarioEstaLivreNoDiaEHorarioCenario1(){

        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var minutos30 = 30;

        var minutos31 = 31;

        var terminoDoServico30MinutosDepois = proximaSegundaAs10.plusMinutes(minutos30);

        var idFuncionario = Long.valueOf(4);

        var servico = cadastrarServico("CORTE", BigDecimal.valueOf(40), minutos30);

        var funcionario = cadastrarFuncionario("THIAGO", "61988558466");

        var cliente = cadastrarCliente("FELIPE", "61985056428", "85296314700");

        var usuario = cadastrarUsuario("HELDER", "123");

        cadastrarServico(funcionario, servico, cliente, usuario, proximaSegundaAs10, terminoDoServico30MinutosDepois);

        var funcionarioLivre = agendaRepository.existeFuncionarioComServicoNoMesmoHorarioDoMarcado(idFuncionario, proximaSegundaAs10, terminoDoServico30MinutosDepois);

//        var funcionarioLivre = agendaRepository.findByFuncionarioIdAndDataBetweenOrTerminoBetweenAndMotivoCancelamento(idFuncionario,
//                proximaSegundaAs10, terminoDoServico30MinutosDepois, proximaSegundaAs10, terminoDoServico30MinutosDepois);

        assertThat(funcionarioLivre).isNotEmpty();

    }

    @Test
    @DisplayName("Deveria devolver false quando o funcionário está com horário fora de período marcado")
    void testarSeFuncionarioEstaLivreNoDiaEHorarioCenario2(){

        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var minutos30 = 30;

        var minutos31 = 31;

        var terminoDoServico30MinutosDepois = proximaSegundaAs10.plusMinutes(minutos30);

        var idFuncionario = Long.valueOf(4);

        var servico = cadastrarServico("CORTE", BigDecimal.valueOf(40), minutos30);

        var funcionario = cadastrarFuncionario("THIAGO", "61988558466");

        var cliente = cadastrarCliente("FELIPE", "61985056428", "85296314700");

        var usuario = cadastrarUsuario("HELDER", "123");

        cadastrarServico(funcionario, servico, cliente, usuario, proximaSegundaAs10, terminoDoServico30MinutosDepois);

        var funcionarioLivre = agendaRepository.existeFuncionarioComServicoNoMesmoHorarioDoMarcado(idFuncionario,
                proximaSegundaAs10.plusMinutes(minutos31), terminoDoServico30MinutosDepois.plusMinutes(minutos31));

//        var funcionarioLivre = agendaRepository.findByFuncionarioIdAndDataBetweenOrTerminoBetweenAndMotivoCancelamento(idFuncionario,
//                proximaSegundaAs10.plusMinutes(minutos31), terminoDoServico30MinutosDepois.plusMinutes(minutos31), proximaSegundaAs10.plusMinutes(minutos31), terminoDoServico30MinutosDepois.plusMinutes(minutos31));

        assertThat(funcionarioLivre).isEmpty();

    }


    private void cadastrarServico(Funcionario funcionario, Servico servico, Cliente cliente, Usuario usuario, LocalDateTime inicio, LocalDateTime termino){
        em.persist(new Agendamento(null, funcionario, servico, cliente, usuario, inicio, termino, null));

    }

    private Servico cadastrarServico(String nome, BigDecimal valor, Integer duracao){
        var servico = new Servico(dadosServico(nome, valor, duracao));
        em.persist(servico);
        return servico;
    }

    private Funcionario cadastrarFuncionario(String nome, String telefone){
        var funcionario = new Funcionario(dadosFuncionario(nome, telefone));
        em.persist(funcionario);
        return funcionario;
    }

    private Cliente cadastrarCliente(String nome, String telefone, String cpf){
        var cliente = new Cliente(dadosCliente(nome, telefone, cpf));
        em.persist(cliente);
        return cliente;
    }

    private Usuario cadastrarUsuario(String login, String senha){
        var usuario = new Usuario(dadosUsuario(login, senha));
        em.persist(usuario);
        return usuario;
    }

    private DadosCadastroFuncionario dadosFuncionario(String nome, String telefone) {
        return new DadosCadastroFuncionario(
                nome,
                telefone
        );
    }

    private DadosCadastroServico dadosServico(String nome, BigDecimal valor, Integer duracao) {
        return new DadosCadastroServico(
                nome,
                valor,
                duracao
        );
    }

    private DadosCadastroCliente dadosCliente(String nome, String telefone, String cpf) {
        return new DadosCadastroCliente(
                nome,
                telefone,
                cpf
        );
    }

    private DadosAutenticacao dadosUsuario(String login, String senha){
        return new DadosAutenticacao(
                login,
                senha
        );
    }


}
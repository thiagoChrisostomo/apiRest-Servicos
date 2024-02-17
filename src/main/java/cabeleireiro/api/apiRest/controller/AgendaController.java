package cabeleireiro.api.apiRest.controller;

import cabeleireiro.api.apiRest.domain.agenda.Agenda;
import cabeleireiro.api.apiRest.domain.agenda.DadosAgendamento;
import cabeleireiro.api.apiRest.domain.agenda.Validacoes.cancelamento.CancelamentoDeServicos;
import cabeleireiro.api.apiRest.domain.agenda.cancelamento.DadosCancelamentoAgendamento;
import cabeleireiro.api.apiRest.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendas")
@SecurityRequirement(name = "bearer-key")
public class AgendaController {

    @Autowired
    private Agenda agenda;

    @Autowired
    private CancelamentoDeServicos cancelamento;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@Valid @RequestBody DadosAgendamento dados, @AuthenticationPrincipal Usuario logado){
        var dto = agenda.agendar(dados, logado);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@Valid @RequestBody DadosCancelamentoAgendamento dados){
        var dto = cancelamento.cancelar(dados);
        return ResponseEntity.ok(dto);
    }

}

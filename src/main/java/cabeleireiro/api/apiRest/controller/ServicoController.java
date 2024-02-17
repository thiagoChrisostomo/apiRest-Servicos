package cabeleireiro.api.apiRest.controller;

import cabeleireiro.api.apiRest.domain.servico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("servicos")
@SecurityRequirement(name = "bearer-key")
public class ServicoController {

    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@Valid @RequestBody DadosCadastroServico dados){
        repository.save(new Servico(dados));
    }

    @GetMapping
    public Page<DadosListagemServico> listar(@PageableDefault(size = 5, sort = {"servico"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemServico::new);
    }

    @PutMapping
    @Transactional
    public void alterar(@Valid @RequestBody DadosAtualizarServico dados){
        var servico = repository.getReferenceById(dados.id());
        servico.atualizarInformacoes(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var servico = repository.getReferenceById(id);
        servico.excluir();
    }

}

package cabeleireiro.api.apiRest.domain.servico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    Page<Servico> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select s.ativo
            from Servico s
            where
            s.id = :id
            """)
    Boolean findAtivoById(Long id);

    @Query("select duracao s from Servico s WHERE s.id = :idServico")
    Integer findByIdDuracao(Long idServico);
}

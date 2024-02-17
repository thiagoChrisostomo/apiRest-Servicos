package cabeleireiro.api.apiRest.domain.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Page<Funcionario> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select f.ativo
            from Funcionario f
            where
            f.id = :id
            """)
    Boolean findAtivoById(Long id);
}

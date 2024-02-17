package cabeleireiro.api.apiRest.domain.agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendaRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = """
            SELECT a.id
            FROM Agendamento a
            WHERE
            a.funcionario.id = :idFuncionario
            AND a.data BETWEEN :data AND :termino
            OR a.termino BETWEEN :data AND :termino
            AND a.motivoCancelamento = null
            """)
    List existeFuncionarioComServicoNoMesmoHorarioDoMarcado(Long idFuncionario, LocalDateTime data, LocalDateTime termino);

//    boolean existsByFuncionarioIdAndDataBetweenOrTerminoBetween(Long idFuncionario, LocalDateTime inicio1, LocalDateTime termino1, LocalDateTime inicio2, LocalDateTime termino2);

    @Query("""
          SELECT a.motivoCancelamento
          FROM Agendamento a
          WHERE
          a.id = :id
          """)
    String existsByIdAndMotivoCancelamento(Long id);

    @Query("""
            SELECT a.data
            FROM Agendamento a
            WHERE
            a.id = :id
           """)
    LocalDateTime findByIdDataDoServicoMarcado(Long id);
}

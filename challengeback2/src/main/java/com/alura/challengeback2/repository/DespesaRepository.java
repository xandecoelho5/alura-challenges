package com.alura.challengeback2.repository;

import com.alura.challengeback2.model.Despesa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends GenericRepository<Despesa, Long> {

    @Override
    @Query(value = "SELECT * FROM DESPESA d " +
            "WHERE (d.DESCRICAO = :descricao) AND (EXTRACT(YEAR FROM d.DATA) = :ano) AND (EXTRACT(MONTH FROM d.DATA) = :mes)", nativeQuery = true)
    Optional<Despesa> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes);

    @Override
    @Query(value = "SELECT * FROM DESPESA d WHERE (EXTRACT(YEAR FROM d.DATA) = :ano) AND (EXTRACT(MONTH FROM d.DATA) = :mes)", nativeQuery = true)
    List<Despesa> findAllByAnoAndMes(Long ano, Long mes);
}

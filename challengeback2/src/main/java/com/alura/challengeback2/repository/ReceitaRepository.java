package com.alura.challengeback2.repository;

import com.alura.challengeback2.model.Receita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends GenericRepository<Receita, Long> {

    @Override
    @Query(value = "SELECT * FROM RECEITA r " +
            "WHERE (r.DESCRICAO = :descricao) AND (EXTRACT(YEAR FROM r.DATA) = :ano) AND (EXTRACT(MONTH FROM r.DATA) = :mes)", nativeQuery = true)
    Optional<Receita> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes);

    @Override
    @Query(value = "SELECT * FROM RECEITA r WHERE (EXTRACT(YEAR FROM r.DATA) = :ano) AND (EXTRACT(MONTH FROM r.DATA) = :mes)", nativeQuery = true)
    List<Receita> findAllByAnoAndMes(Long ano, Long mes);
}

package com.alura.challengeback2.repository;

import com.alura.challengeback2.model.Receita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReceitaRepository extends GenericRepository<Receita, Long> {

    @Override
    @Query("SELECT r FROM Receita r WHERE (r.descricao = :descricao) AND YEAR(r.data) = :ano and MONTH(r.data) = :mes")
    Optional<Receita> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes);

    @Override
    @Query("SELECT r FROM Receita r WHERE YEAR(r.data) = :ano and MONTH(r.data) = :mes")
    List<Receita> findAllByAnoAndMes(Integer ano, Integer mes);

    @Query("select sum(r.valor) from Receita r where YEAR(r.data) = :ano and MONTH(r.data) = :mes")
    Optional<BigDecimal> somatorioDoMes(Integer ano, Integer mes);
}

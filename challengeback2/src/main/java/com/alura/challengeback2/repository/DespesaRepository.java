package com.alura.challengeback2.repository;

import com.alura.challengeback2.dto.GastoPorCategoriaDto;
import com.alura.challengeback2.model.Despesa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DespesaRepository extends GenericRepository<Despesa, Long> {

    @Override
    @Query("SELECT d FROM Despesa d WHERE (d.descricao = :descricao) AND YEAR(d.data) = :ano and MONTH(d.data) = :mes")
    Optional<Despesa> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes);

    @Override
    @Query("SELECT d FROM Despesa d WHERE YEAR(d.data) = :ano and MONTH(d.data) = :mes")
    List<Despesa> findAllByAnoAndMes(Integer ano, Integer mes);

    @Query("select sum(d.valor) from Despesa d where YEAR(d.data) = :ano and MONTH(d.data) = :mes")
    Optional<BigDecimal> somatorioDoMes(Integer ano, Integer mes);

    @Query("select new com.alura.challengeback2.dto.GastoPorCategoriaDto(d.categoria, sum(d.valor)) from Despesa d " +
            " where YEAR(d.data) = :ano and MONTH(d.data) = :mes " +
            " group by d.categoria")
    List<GastoPorCategoriaDto> gastosPorCategoriaNoMes(Integer ano, Integer mes);
}

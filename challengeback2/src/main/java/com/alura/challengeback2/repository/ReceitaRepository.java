package com.alura.challengeback2.repository;

import com.alura.challengeback2.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query(value = "SELECT * FROM RECEITA r WHERE (r.DESCRICAO = :descricao) AND (EXTRACT(MONTH FROM r.DATA) = :mes)", nativeQuery = true)
    Optional<Receita> findByDescricaoAndDataMes(String descricao, Integer mes);
}

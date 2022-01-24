package com.alura.challengeback2.repository;

import com.alura.challengeback2.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query(value = "SELECT * FROM DESPESA d WHERE (d.DESCRICAO = :descricao) AND (EXTRACT(MONTH FROM d.DATA) = :mes)", nativeQuery = true)
    Optional<Despesa> findByDescricaoAndDataMes(String descricao, Integer mes);
}

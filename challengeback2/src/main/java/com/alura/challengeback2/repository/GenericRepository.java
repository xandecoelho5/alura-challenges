package com.alura.challengeback2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository <T, ID> extends JpaRepository<T, ID> {

    Optional<T> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes);

    List<T> findAllByDescricaoContaining(String descricao);

    List<T> findAllByAnoAndMes(Long ano, Long mes);
}

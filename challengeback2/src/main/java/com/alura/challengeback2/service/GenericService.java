package com.alura.challengeback2.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericService <T, ID extends Serializable> {

    List<T> findAll();

    T save(T receita);

    Optional<T> findById(ID id);

    void deleteById(ID id);

    Optional<T> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes);

    List<T> findAllByDescricaoContaining(String descricao);

    List<T> findAllByAnoAndMes(Long ano, Long mes);
}

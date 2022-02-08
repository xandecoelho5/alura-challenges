package com.alura.challengeback2.service;

import java.util.List;
import java.util.Optional;

public interface GenericService <T, ID, Y> {

    List<Y> salvarTodos(Iterable<Y> dtoList);

    List<Y> listar();

    Y cadastrar(Y dto);

    Optional<Y> atualizar(ID id, Y dto);

    Optional<Y> findById(ID id);

    Optional<Boolean> excluir(ID id);

    Optional<T> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes);

    List<Y> findAllByDescricaoContaining(String descricao);

    List<Y> findAllByAnoAndMes(Integer ano, Integer mes);
}

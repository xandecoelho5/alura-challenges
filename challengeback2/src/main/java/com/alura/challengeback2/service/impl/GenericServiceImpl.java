package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.repository.GenericRepository;
import com.alura.challengeback2.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericService<T, ID> {

    protected abstract GenericRepository<T, ID> getRepository();

    @Override
    @Transactional
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional
    public T save(T receita) {
        return getRepository().save(receita);
    }

    @Override
    @Transactional
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public Optional<T> findByDescricaoAndDataMes(String descricao, Integer ano, Integer mes) {
        return getRepository().findByDescricaoAndDataMes(descricao, ano, mes);
    }

    @Override
    public List<T> findAllByDescricaoContaining(String descricao) {
        return getRepository().findAllByDescricaoContaining(descricao);
    }

    @Override
    public List<T> findAllByAnoAndMes(Long ano, Long mes) {
        return getRepository().findAllByAnoAndMes(ano, mes);
    }
}

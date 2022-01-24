package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.service.GenericService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl <T, ID extends Serializable> implements GenericService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

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
}

package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.repository.DespesaRepository;
import com.alura.challengeback2.repository.GenericRepository;
import com.alura.challengeback2.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DespesaServiceImpl extends GenericServiceImpl<Despesa, Long> implements DespesaService {

    private final DespesaRepository repository;

    @Override
    protected GenericRepository<Despesa, Long> getRepository() {
        return repository;
    }
}

package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.repository.GenericRepository;
import com.alura.challengeback2.repository.ReceitaRepository;
import com.alura.challengeback2.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceitaServiceImpl extends GenericServiceImpl<Receita, Long> implements ReceitaService {

    private final ReceitaRepository repository;

    @Override
    protected GenericRepository<Receita, Long> getRepository() {
        return repository;
    }
}

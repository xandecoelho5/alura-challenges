package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.repository.DespesaRepository;
import com.alura.challengeback2.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DespesaServiceImpl extends GenericServiceImpl<Despesa, Long> implements DespesaService {

    private final DespesaRepository repository;

    @Override
    protected JpaRepository<Despesa, Long> getRepository() {
        return repository;
    }

    @Override
    public Optional<Despesa> findByDescricaoAndDataMes(String descricao, Integer mes) {
        return repository.findByDescricaoAndDataMes(descricao, mes);
    }
}

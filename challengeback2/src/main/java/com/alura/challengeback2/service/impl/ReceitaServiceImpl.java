package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.repository.ReceitaRepository;
import com.alura.challengeback2.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaServiceImpl extends GenericServiceImpl<Receita, Long> implements ReceitaService {

    private final ReceitaRepository receitaRepository;

    @Override
    protected JpaRepository<Receita, Long> getRepository() {
        return receitaRepository;
    }

    @Override
    public Optional<Receita> findByDescricaoAndDataMes(String descricao, Integer mes) {
        return receitaRepository.findByDescricaoAndDataMes(descricao, mes);
    }
}

package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.model.TO.CategoriaValor;
import com.alura.challengeback2.model.TO.Resumo;
import com.alura.challengeback2.model.TO.ResumoValores;
import com.alura.challengeback2.repository.ResumoRepository;
import com.alura.challengeback2.service.ResumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumoServiceImpl implements ResumoService {

    private final ResumoRepository repository;

    @Override
    public Resumo resumoDoMes(Long ano, Long mes) {
        ResumoValores resumoValoresPorMes = repository.findResumoValoresPorMes(ano, mes);
        List<CategoriaValor> categoriaValorPorMes = repository.findCategoriaValorPorMes(ano, mes);

        return Resumo.builder()
                .resumoValores(resumoValoresPorMes)
                .categoriaValores(categoriaValorPorMes)
                .build();
    }
}

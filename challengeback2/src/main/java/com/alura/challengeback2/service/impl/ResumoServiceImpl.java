package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.dto.GastoPorCategoriaDto;
import com.alura.challengeback2.dto.ResumoDto;
import com.alura.challengeback2.repository.DespesaRepository;
import com.alura.challengeback2.repository.ReceitaRepository;
import com.alura.challengeback2.service.ResumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumoServiceImpl implements ResumoService {

    private final ReceitaRepository receitaRepository;
    private final DespesaRepository despesaRepository;

    @Override
    public ResumoDto resumoDoMes(Integer ano, Integer mes) {
        BigDecimal somatorioReceitas = receitaRepository.somatorioDoMes(ano, mes).orElse(BigDecimal.ZERO);
        BigDecimal somatorioDespesas = despesaRepository.somatorioDoMes(ano, mes).orElse(BigDecimal.ZERO);
        BigDecimal saldoFinal = somatorioReceitas.subtract(somatorioDespesas);
        List<GastoPorCategoriaDto> gastosPorCategoria = despesaRepository.gastosPorCategoriaNoMes(ano, mes);
        return new ResumoDto(somatorioReceitas, somatorioDespesas, saldoFinal, gastosPorCategoria);
    }
}

package com.alura.challengeback2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class ResumoDto {

    private BigDecimal valorTotalReceitas;
    private BigDecimal valorTotalDespesas;
    private BigDecimal saldoFinal;
    private List<GastoPorCategoriaDto> gastoPorCategoria;
}

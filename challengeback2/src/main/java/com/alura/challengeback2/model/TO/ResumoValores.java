package com.alura.challengeback2.model.TO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class ResumoValores {

    private double valorTotalReceitas;
    private double valorTotalDespesas;
    private double saldoFinal;
}

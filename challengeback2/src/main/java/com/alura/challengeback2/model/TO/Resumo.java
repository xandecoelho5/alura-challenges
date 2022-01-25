package com.alura.challengeback2.model.TO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Resumo {

    private ResumoValores resumoValores;
    private List<CategoriaValor> categoriaValores;
}

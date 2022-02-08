package com.alura.challengeback2.dto;

import com.alura.challengeback2.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class GastoPorCategoriaDto {

    private Categoria categoria;
    private BigDecimal valor;
}

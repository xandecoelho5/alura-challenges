package com.alura.challengeback2.dto;

import com.alura.challengeback2.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GastoPorCategoriaDto {

    private Categoria categoria;
    private double valor;
}

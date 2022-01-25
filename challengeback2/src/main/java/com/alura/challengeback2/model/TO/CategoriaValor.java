package com.alura.challengeback2.model.TO;

import com.alura.challengeback2.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoriaValor {

    private Categoria categoria;
    private double valor;
}

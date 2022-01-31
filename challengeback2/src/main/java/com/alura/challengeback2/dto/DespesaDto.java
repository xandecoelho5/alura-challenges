package com.alura.challengeback2.dto;

import com.alura.challengeback2.model.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DespesaDto extends RecursoDto {

    private Categoria categoria;
}

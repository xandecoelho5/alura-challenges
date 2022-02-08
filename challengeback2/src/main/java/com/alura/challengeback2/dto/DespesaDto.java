package com.alura.challengeback2.dto;

import com.alura.challengeback2.model.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DespesaDto extends RecursoDto {

    private Categoria categoria;
}

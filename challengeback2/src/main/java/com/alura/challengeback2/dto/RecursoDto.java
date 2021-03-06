package com.alura.challengeback2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RecursoDto {

    private String id;

    @NotEmpty(message = "O campo descrição deve ser informado!")
    private String descricao;

    @Positive(message = "O campo valor deve ser informado!")
    @NotNull
    private BigDecimal valor;

    @NotNull(message = "O campo data deve ser informado!")
    private LocalDate data;
}

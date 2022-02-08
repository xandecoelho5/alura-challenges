package com.alura.challengeback2.util;

import com.alura.challengeback2.dto.ReceitaDto;
import com.alura.challengeback2.model.Receita;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReceitaUtil {

    public static Receita getReceita(int month, String descricao) {

        return Receita.builder()
                .valor(BigDecimal.TEN)
                .data(LocalDate.of(2022, month, 20))
                .descricao(descricao)
                .build();
    }

    public static ReceitaDto getReceitaDto(int month, String descricao) {

        return ReceitaDto.builder()
                .valor(BigDecimal.TEN)
                .data(LocalDate.of(2022, month, 20))
                .descricao(descricao)
                .build();
    }
}

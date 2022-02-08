package com.alura.challengeback2.util;

import com.alura.challengeback2.dto.DespesaDto;
import com.alura.challengeback2.model.Categoria;
import com.alura.challengeback2.model.Despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DespesaUtil {

    public static Despesa getDespesa(int month, String descricao, Categoria categoria) {

        return Despesa.builder()
                .valor(BigDecimal.TEN)
                .data(LocalDate.of(2022, month, 20))
                .descricao(descricao)
                .categoria(categoria)
                .build();
    }

    public static DespesaDto getDespesaDto(int month, String descricao, Categoria categoria) {

        return DespesaDto.builder()
                .valor(BigDecimal.TEN)
                .data(LocalDate.of(2022, month, 20))
                .descricao(descricao)
                .categoria(categoria)
                .build();
    }
}

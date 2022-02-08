package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.dto.ResumoDto;
import com.alura.challengeback2.model.Categoria;
import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.repository.DespesaRepository;
import com.alura.challengeback2.repository.ReceitaRepository;
import com.alura.challengeback2.util.DbUtil;
import com.alura.challengeback2.util.DespesaUtil;
import com.alura.challengeback2.util.ReceitaUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ResumoServiceImplTest {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    private ResumoServiceImpl service;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void beforeEach() {
        this.service = new ResumoServiceImpl(receitaRepository, despesaRepository);

        Despesa despesa1 = DespesaUtil.getDespesa(1, "teste1", Categoria.ALIMENTACAO);
        Despesa despesa2 = DespesaUtil.getDespesa(1, "teste2", Categoria.EDUCACAO);
        Despesa despesa3 = DespesaUtil.getDespesa(2, "teste3", Categoria.SAUDE);
        Despesa despesa4 = DespesaUtil.getDespesa(1, "teste4", Categoria.ALIMENTACAO);

        Receita receita1 = ReceitaUtil.getReceita(1, "teste1");
        Receita receita2 = ReceitaUtil.getReceita(1, "teste2");
        Receita receita3 = ReceitaUtil.getReceita(2, "teste3");

        DbUtil.salvarListaNoBanco(em, List.of(despesa1, despesa2, despesa3, despesa4));
        DbUtil.salvarListaNoBanco(em, List.of(receita1, receita2, receita3));
    }

    @Test
    void deveRetornarResumoDoMesQuandoHouverDadosCadastrados() {

        ResumoDto resumoDto = service.resumoDoMes(2022, 1);

        assertEquals(new BigDecimal("20").setScale(2, RoundingMode.HALF_UP), resumoDto.getValorTotalReceitas());
        assertEquals(new BigDecimal("30").setScale(2, RoundingMode.HALF_UP), resumoDto.getValorTotalDespesas());
        assertEquals(new BigDecimal("-10").setScale(2, RoundingMode.HALF_UP), resumoDto.getSaldoFinal());
        assertEquals(2, resumoDto.getGastoPorCategoria().size());
    }

    @Test
    void deveRetornar0EListaVaziaQuandoNaoHouverDadosCadastrados() {

        ResumoDto resumoDto = service.resumoDoMes(2022, 3);

        assertEquals(BigDecimal.ZERO, resumoDto.getValorTotalReceitas());
        assertEquals(BigDecimal.ZERO, resumoDto.getValorTotalDespesas());
        assertEquals(BigDecimal.ZERO, resumoDto.getSaldoFinal());
        assertTrue(resumoDto.getGastoPorCategoria().isEmpty());
    }
}

package com.alura.challengeback2.repository;

import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.util.DbUtil;
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
class ReceitaRepositoryTest {

    @Autowired
    private ReceitaRepository repository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void beforeEach() {
        Receita receita1 = ReceitaUtil.getReceita(1, "teste1");
        Receita receita2 = ReceitaUtil.getReceita(1, "teste2");
        Receita receita3 = ReceitaUtil.getReceita(2, "teste3");
        Receita receita4 = ReceitaUtil.getReceita(1, "teste4");

        DbUtil.salvarListaNoBanco(em, List.of(receita1, receita2, receita3, receita4));
    }

    @Test
    void deveRetornarSomatorioQuandoHouverReceitasNoMes() {

        assertEquals(new BigDecimal("30").setScale(2, RoundingMode.HALF_UP), repository.somatorioDoMes(2022, 1).get());
    }

    @Test
    void deveRetornar0ParaSomatorioQuandoNaoHouverReceitasNoMes() {

        assertTrue(repository.somatorioDoMes(2022, 3).isEmpty());
    }
}

package com.alura.challengeback2.repository;

import com.alura.challengeback2.dto.GastoPorCategoriaDto;
import com.alura.challengeback2.model.Categoria;
import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.util.DbUtil;
import com.alura.challengeback2.util.DespesaUtil;
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
class DespesaRepositoryTest {

    @Autowired
    private DespesaRepository repository;

    @Autowired
    private TestEntityManager em;

    @BeforeEach
    void beforeEach() {
        Despesa despesa1 = DespesaUtil.getDespesa(1, "teste1", Categoria.ALIMENTACAO);
        Despesa despesa2 = DespesaUtil.getDespesa(1, "teste2", Categoria.EDUCACAO);
        Despesa despesa3 = DespesaUtil.getDespesa(2, "teste3", Categoria.ALIMENTACAO);
        Despesa despesa4 = DespesaUtil.getDespesa(1, "teste4", Categoria.ALIMENTACAO);

        DbUtil.salvarListaNoBanco(em, List.of(despesa1, despesa2, despesa3, despesa4));
    }

    @Test
    void deveRetornarSomatorioQuandoHouverDespesasNoMes() {

        assertEquals(new BigDecimal("30").setScale(2, RoundingMode.HALF_UP), repository.somatorioDoMes(2022, 1).get());
    }

    @Test
    void deveRetornar0ParaSomatorioQuandoNaoHouverDespesasNoMes() {

        assertTrue(repository.somatorioDoMes(2022, 3).isEmpty());
    }

    @Test
    void deveRetornarListaGastoPorCategoriaNoMes() {

        GastoPorCategoriaDto gastoPorCategoriaDto1 = GastoPorCategoriaDto.builder()
                .categoria(Categoria.ALIMENTACAO)
                .valor(new BigDecimal("20"))
                .build();

        GastoPorCategoriaDto gastoPorCategoriaDto2 = GastoPorCategoriaDto.builder()
                .categoria(Categoria.EDUCACAO)
                .valor(BigDecimal.TEN)
                .build();

        List<GastoPorCategoriaDto> listaDto = List.of(gastoPorCategoriaDto1, gastoPorCategoriaDto2);
        List<GastoPorCategoriaDto> gastosPorCategoriaNoMes = repository.gastosPorCategoriaNoMes(2022, 1);

        for (int i = 0; i < listaDto.size(); i++) {
            assertEquals(listaDto.get(i).getCategoria(), gastosPorCategoriaNoMes.get(i).getCategoria());
            assertEquals(listaDto.get(i).getValor().setScale(2, RoundingMode.HALF_UP), gastosPorCategoriaNoMes.get(i).getValor().setScale(2, RoundingMode.HALF_UP));
        }
    }

    @Test
    void deveRetornarVazioQuandoNaoHouverDespesaNoMes() {

        assertTrue(repository.gastosPorCategoriaNoMes(2022, 3).isEmpty());
    }
}

package com.alura.challengeback2.repository;

import com.alura.challengeback2.model.Categoria;
import com.alura.challengeback2.model.TO.CategoriaValor;
import com.alura.challengeback2.model.TO.ResumoValores;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumoRepository {

    private final EntityManager entityManager;

    private static final String SUM_VALOR_TOTAL_RECEITAS = "(SELECT SUM(r.VALOR) " +
            "FROM RECEITA r " +
            "WHERE EXTRACT(YEAR FROM r.DATA) = :ano AND EXTRACT(MONTH FROM r.DATA) = :mes)";

    private static final String SUM_VALOR_TOTAL_DESPESAS = "(SELECT SUM(d.VALOR) " +
            "FROM DESPESA d " +
            "WHERE EXTRACT(YEAR FROM d.DATA) = :ano AND EXTRACT(MONTH FROM d.DATA) = :mes)";

    public ResumoValores findResumoValoresPorMes(Long ano, Long mes) {
        String query = "SELECT" +
                SUM_VALOR_TOTAL_RECEITAS + " AS valorTotalReceitas, " +
                SUM_VALOR_TOTAL_DESPESAS + " AS valorTotalDespesas, " +
                "(" + SUM_VALOR_TOTAL_RECEITAS + " - " + SUM_VALOR_TOTAL_DESPESAS + ") AS saldoFinal";

        var res = (Object[]) entityManager.createNativeQuery(query)
                .setParameter("ano", ano)
                .setParameter("mes", mes)
                .getSingleResult();

        ResumoValores result = null;
        result = ResumoValores.builder()
                .valorTotalReceitas((double) res[0])
                .valorTotalDespesas((double) res[1])
                .saldoFinal((double) res[2])
                .build();
        return result;
    }

    public List<CategoriaValor> findCategoriaValorPorMes(Long ano, Long mes) {
        String query = "SELECT d.CATEGORIA, SUM(d.VALOR) valor " +
                "FROM DESPESA d " +
                "WHERE EXTRACT(YEAR FROM d.DATA) = :ano AND EXTRACT(MONTH FROM d.DATA) = :mes " +
                "GROUP BY d.CATEGORIA";

        @SuppressWarnings("unchecked")
        List<Object[]> list = entityManager.createNativeQuery(query)
                .setParameter("ano", ano)
                .setParameter("mes", mes)
                .getResultList();

        List<CategoriaValor> result = new ArrayList<>();
        list.forEach(item -> result.add(new CategoriaValor(Categoria.valueOf((String) item[0]), (double) item[1])));
        return result;
    }
}

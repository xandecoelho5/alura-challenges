package com.alura.challengeback2.controller;

import com.alura.challengeback2.exception.RegistroComDescricaoIgualNoMesmoMesException;
import com.alura.challengeback2.exception.RegistroNaoEncontradoException;
import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.service.GenericService;
import com.alura.challengeback2.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("receitas")
@RequiredArgsConstructor
public class ReceitaController extends GenericController<Receita, Long> {

    private final ReceitaService service;

    @Override
    protected GenericService<Receita, Long> getService() {
        return service;
    }

    @Override
    protected Receita validaSeJaExiste(Long id) {
        return service.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Receita", id));
    }

    @Override
    protected void validaJaExisteRegistroComDescricaoParaOMes(Receita receita) {
        service.findByDescricaoAndDataMes(receita.getDescricao(), receita.getData().getMonthValue())
                .ifPresent(r -> {
                    throw new RegistroComDescricaoIgualNoMesmoMesException("receita");
                });
    }
}

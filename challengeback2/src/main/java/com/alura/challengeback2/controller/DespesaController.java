package com.alura.challengeback2.controller;

import com.alura.challengeback2.exception.RegistroComDescricaoIgualNoMesmoMesException;
import com.alura.challengeback2.exception.RegistroNaoEncontradoException;
import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.service.DespesaService;
import com.alura.challengeback2.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController extends GenericController<Despesa, Long> {

    private final DespesaService service;

    @Override
    protected GenericService<Despesa, Long> getService() {
        return service;
    }

    @Override
    protected Despesa validaSeJaExiste(Long id) {
        return getService().findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Despesa", id));
    }

    @Override
    protected void validaJaExisteRegistroComDescricaoParaOMes(Despesa despesa) {
        service.findByDescricaoAndDataMes(despesa.getDescricao(), despesa.getData().getYear(), despesa.getData().getMonthValue())
                .ifPresent(r -> {
                    throw new RegistroComDescricaoIgualNoMesmoMesException("despesa");
                });
    }
}

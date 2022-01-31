package com.alura.challengeback2.controller;

import com.alura.challengeback2.dto.DespesaDto;
import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.service.DespesaService;
import com.alura.challengeback2.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("despesas")
@RequiredArgsConstructor
public class DespesaController extends GenericController<Despesa, Long, DespesaDto> {

    private final DespesaService service;

    @Override
    protected GenericService<Despesa, Long, DespesaDto> getService() {
        return service;
    }
}

package com.alura.challengeback2.controller;

import com.alura.challengeback2.dto.ReceitaDto;
import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.service.GenericService;
import com.alura.challengeback2.service.ReceitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("receitas")
@RequiredArgsConstructor
public class ReceitaController extends GenericController<Receita, Long, ReceitaDto> {

    private final ReceitaService service;

    @Override
    protected GenericService<Receita, Long, ReceitaDto> getService() {
        return service;
    }
}

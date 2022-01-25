package com.alura.challengeback2.controller;

import com.alura.challengeback2.model.TO.Resumo;
import com.alura.challengeback2.service.ResumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resumo/{ano}/{mes}")
@RequiredArgsConstructor
public class ResumoController {

    private final ResumoService service;

    @GetMapping
    public Resumo resumoDoMes(@PathVariable("ano") Long ano, @PathVariable("mes") Long mes) {
        return service.resumoDoMes(ano, mes);
    }
}

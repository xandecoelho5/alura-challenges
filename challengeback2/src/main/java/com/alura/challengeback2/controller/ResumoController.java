package com.alura.challengeback2.controller;

import com.alura.challengeback2.dto.ResumoDto;
import com.alura.challengeback2.service.ResumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("resumo")
@RequiredArgsConstructor
public class ResumoController {

    private final ResumoService service;

    @GetMapping("/{ano}/{mes}")
    public ResumoDto resumoDoMes(@PathVariable("ano") Integer ano, @PathVariable("mes") Integer mes) {
        return service.resumoDoMes(ano, mes);
    }
}

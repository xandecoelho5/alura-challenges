package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.repository.DespesaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DespesaServiceImplTest {

    @Mock
    private DespesaRepository repository;

    @InjectMocks
    private DespesaServiceImpl service;

    @Test
    void deveRetornarTodasDespesas() {
//        Despesa despesa1 = Despesa.builder()
//                .id(1L)
//                .data(LocalDate.now())
//                .descricao("teste")
//                .build();
//
//        Despesa despesa2 = Despesa.builder()
//                .id(1L)
//                .data(LocalDate.now())
//                .descricao("teste2")
//                .build();
//
//        var lista = List.of(despesa1, despesa2);
//
//        service.saveAll(lista);
//
//        Mockito.when(service.findAll()).thenReturn(lista);
//
//        assertEquals(lista, service.findAll());
    }
}

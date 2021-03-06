package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.dto.DespesaDto;
import com.alura.challengeback2.model.Categoria;
import com.alura.challengeback2.model.Despesa;
import com.alura.challengeback2.repository.DespesaRepository;
import com.alura.challengeback2.util.DbUtil;
import com.alura.challengeback2.util.DespesaUtil;
import com.alura.challengeback2.util.MapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DespesaServiceImplTest {

    @Autowired
    private DespesaRepository repository;

    private DespesaServiceImpl service;

    @Autowired
    private TestEntityManager em;

    private MapperUtil<Despesa, DespesaDto> mapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        service = new DespesaServiceImpl(repository, modelMapper);
        this.mapper = new MapperUtil<>(modelMapper, Despesa.class, DespesaDto.class);
    }

    @Test
    void deveRetornarTodasDespesasDoMes() {

        DespesaDto despesaDto1 = DespesaUtil.getDespesaDto(1, "teste1", Categoria.OUTRAS);
        DespesaDto despesaDto2 = DespesaUtil.getDespesaDto(2, "teste2", Categoria.ALIMENTACAO);

        DbUtil.salvarListaNoBanco(em, List.of(mapper.mapDtoToEntity(despesaDto1), mapper.mapDtoToEntity(despesaDto2)));

        assertEquals(1, service.findAllByAnoAndMes(2022, 1).size());
    }

    @Test
    void deveRetornarVazioSeNaoHouverDespesasNoMes() {

        DespesaDto despesaDto1 = DespesaUtil.getDespesaDto(1, "teste1", Categoria.OUTRAS);
        DespesaDto despesaDto2 = DespesaUtil.getDespesaDto(1, "teste2", Categoria.ALIMENTACAO);

        DbUtil.salvarListaNoBanco(em, List.of(mapper.mapDtoToEntity(despesaDto1), mapper.mapDtoToEntity(despesaDto2)));

        assertTrue(service.findAllByAnoAndMes(2022, 2).isEmpty());
    }


    @Test
    void deveRetornarDespesaSeEncontrarRegistroComDescricao() {
        DespesaDto despesaDto1 = DespesaUtil.getDespesaDto(2, "teste1", Categoria.OUTRAS);

        em.persist(mapper.mapDtoToEntity(despesaDto1));

        assertEquals("teste1", service.findByDescricaoAndDataMes("teste1", 2022, 2).get().getDescricao());
        assertEquals("teste1", service.findAllByDescricaoContaining("teste1").get(0).getDescricao());
    }

    @Test
    void naoDeveRetornarSeNaoHouverDespesaCadastrada() {
        DespesaDto despesaDto1 = DespesaUtil.getDespesaDto(2, "teste1", Categoria.OUTRAS);

        em.persist(mapper.mapDtoToEntity(despesaDto1));

        assertTrue(service.findByDescricaoAndDataMes("teste2", 2022, 2).isEmpty());
        assertTrue(service.findAllByDescricaoContaining("teste2").isEmpty());
    }
}

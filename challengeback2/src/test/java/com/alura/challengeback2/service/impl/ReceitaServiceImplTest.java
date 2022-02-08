package com.alura.challengeback2.service.impl;

import com.alura.challengeback2.dto.ReceitaDto;
import com.alura.challengeback2.model.Receita;
import com.alura.challengeback2.repository.ReceitaRepository;
import com.alura.challengeback2.util.DbUtil;
import com.alura.challengeback2.util.MapperUtil;
import com.alura.challengeback2.util.ReceitaUtil;
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
class ReceitaServiceImplTest {

    @Autowired
    private ReceitaRepository repository;

    private ReceitaServiceImpl service;

    @Autowired
    private TestEntityManager em;

    private MapperUtil<Receita, ReceitaDto> mapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        service = new ReceitaServiceImpl(repository, modelMapper);
        this.mapper = new MapperUtil<>(modelMapper, Receita.class, ReceitaDto.class);
    }

    @Test
    void deveRetornarTodasReceitasDoMes() {

        ReceitaDto receitaDto1 = ReceitaUtil.getReceitaDto(1, "teste1");
        ReceitaDto receitaDto2 = ReceitaUtil.getReceitaDto(2, "teste2");

        DbUtil.salvarListaNoBanco(em, List.of(mapper.mapDtoToEntity(receitaDto1), mapper.mapDtoToEntity(receitaDto2)));

        assertEquals(1, service.findAllByAnoAndMes(2022, 1).size());
    }

    @Test
    void deveRetornarVazioSeNaoHouverReceitasNoMes() {

        ReceitaDto receitaDto1 = ReceitaUtil.getReceitaDto(1, "teste1");
        ReceitaDto receitaDto2 = ReceitaUtil.getReceitaDto(1, "teste2");

        DbUtil.salvarListaNoBanco(em, List.of(mapper.mapDtoToEntity(receitaDto1), mapper.mapDtoToEntity(receitaDto2)));

        assertTrue(service.findAllByAnoAndMes(2022, 2).isEmpty());
    }


    @Test
    void deveRetornarReceitaSeEncontrarRegistroComDescricao() {
        ReceitaDto receitaDto1 = ReceitaUtil.getReceitaDto(2, "teste1");

        em.persist(mapper.mapDtoToEntity(receitaDto1));

        assertEquals("teste1", service.findByDescricaoAndDataMes("teste1", 2022, 2).get().getDescricao());
        assertEquals("teste1", service.findAllByDescricaoContaining("teste1").get(0).getDescricao());
    }

    @Test
    void naoDeveRetornarSeNaoHouverReceitaCadastrada() {
        ReceitaDto receitaDto1 = ReceitaUtil.getReceitaDto(2, "teste1");

        em.persist(mapper.mapDtoToEntity(receitaDto1));

        assertTrue(service.findByDescricaoAndDataMes("teste2", 2022, 2).isEmpty());
        assertTrue(service.findAllByDescricaoContaining("teste2").isEmpty());
    }
}

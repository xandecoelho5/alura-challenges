package com.alura.challengeback2.controller;

import com.alura.challengeback2.util.DbUtil;
import com.alura.challengeback2.util.TestParameters;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenericControllerTest {

    private static final String TEST_PARAMETERS_URL = "com.alura.challengeback2.util.TestParameters#";
    private static final String PARAMS_COM_CODIGO_EXISTENTE = TEST_PARAMETERS_URL + "urlComCodigoExistente";
    private static final String PARAMS_COM_CODIGO_NOVO_REGISTRO = TEST_PARAMETERS_URL + "urlComCodigoNovoRegistro";
    private static final String PARAMS_URL_E_TOTAL_CADASTRADO = TEST_PARAMETERS_URL + "urlETotalCadastrado";
    private static final String PARAMS_URL = TEST_PARAMETERS_URL + "url";
    private final Integer CODIGO_INEXISTENTE = 99;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void setup(@Autowired DataSource dataSource) throws SQLException {
        DbUtil.populaBanco(dataSource);
    }

    @ParameterizedTest
    @MethodSource(PARAMS_URL_E_TOTAL_CADASTRADO)
    @Order(1)
    void deveRetornarTodosRegistros(String url, Integer total) throws Exception {

        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(jsonPath("$", Matchers.hasSize(total)));
    }

    @ParameterizedTest
    @MethodSource(PARAMS_COM_CODIGO_EXISTENTE)
    @Order(2)
    void deveRetornarUmaEntidadeQuandoExistirCodigo(String url) throws Exception {

        mockMvc.perform(get(url))
                .andExpect(status().isFound());
    }

    @ParameterizedTest
    @MethodSource(PARAMS_URL)
    @Order(3)
    void deveRetornar204QuandoNaoExistirCodigoAoBuscarPorCodigo(String url) throws Exception {

        mockMvc.perform(get(url + "/" + CODIGO_INEXISTENTE))
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource(PARAMS_URL)
    @Order(4)
    void deveRetornar201AoCriarComDadosValidos(String url) throws Exception {

        enviarPOSTJson(url, TestParameters.JSON_POST).andExpect(status().isCreated());
    }

    @ParameterizedTest
    @MethodSource(PARAMS_URL)
    @Order(5)
    void deveRetornar400CasoFaltarDadosObrigatorios(String url) throws Exception {

        enviarPOSTJson(url, TestParameters.JSON_SEM_CAMPO_VALOR).andExpect(status().isBadRequest());
        enviarPUTJson(url, TestParameters.JSON_SEM_CAMPO_VALOR, 1).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource(PARAMS_URL)
    @Order(6)
    void deveRetornar400SeJaHouverDescricaoParaOMes(String url) throws Exception {

        enviarPOSTJson(url, TestParameters.JSON_POST)
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("mês")));

        enviarPUTJson(url, TestParameters.JSON_PUT, CODIGO_INEXISTENTE)
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource(PARAMS_COM_CODIGO_NOVO_REGISTRO)
    @Order(7)
    void deveRetornarOKAoAtualizarComDadosValidos(String url, Integer codigo) throws Exception {

        enviarPUTJson(url, TestParameters.JSON_PUT, codigo)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao", is("teste123")));
    }

    @ParameterizedTest
    @MethodSource(PARAMS_URL)
    @Order(8)
    void deveRetornar404QuandoNaoExistir(String url) throws Exception {

        enviarPUTJson(url, TestParameters.JSON_PUT, CODIGO_INEXISTENTE)
                .andExpect(status().isNotFound());
    }

    @ParameterizedTest
    @MethodSource(PARAMS_COM_CODIGO_NOVO_REGISTRO)
    @Order(9)
    void deveRetornar204AoExcluir(String url, Integer codigo) throws Exception {

        mockMvc.perform(delete(url + "/" + codigo))
                .andExpect(status().isNoContent());
    }

    @ParameterizedTest
    @MethodSource(PARAMS_URL)
    @Order(10)
    void deveRetornar404AoNaoEncontrarParaExcluir(String url) throws Exception {

        mockMvc.perform(delete(url + "/" + CODIGO_INEXISTENTE))
                .andExpect(status().isNotFound());
    }

    private ResultActions enviarPOSTJson(String url, String json) throws Exception {
        return mockMvc.perform(post(url)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
    }

    private ResultActions enviarPUTJson(String url, String json, Integer codigo) throws Exception {
        return mockMvc.perform(put(url + "/" + codigo)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
    }
}


package com.alura.challengeback2.util;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class TestParameters {

    public static final String JSON_POST = "{\"descricao\":\"teste999\",\"valor\":1200,\"data\":\"2022-03-03\",\"categoria\":\"IMPREVISTOS\"}";
    public static final String JSON_PUT = "{\"descricao\":\"teste123\",\"valor\":1200,\"data\":\"2022-03-03\",\"categoria\":\"IMPREVISTOS\"}";
    public static final String JSON_SEM_CAMPO_VALOR = "{\"descricao\":\"teste1000\",\"data\":\"2022-03-03\",\"categoria\":\"IMPREVISTOS\"}";
    private static final String URL_DESPESAS = "/despesas";
    private static final String URL_RECEITAS = "/receitas";

    public static Stream<Arguments> urlETotalCadastrado() {
        return Stream.of(
                Arguments.of(URL_DESPESAS, 7),
                Arguments.of(URL_RECEITAS, 3)
        );
    }

    public static Stream<Arguments> urlComCodigoExistente() {
        return Stream.of(
                Arguments.of(URL_DESPESAS + "/1"),
                Arguments.of(URL_RECEITAS + "/8")
        );
    }

    public static Stream<Arguments> urlComCodigoNovoRegistro() {
        return Stream.of(
                Arguments.of(URL_DESPESAS, 11),
                Arguments.of(URL_RECEITAS, 12)
        );
    }

    public static Stream<Arguments> url() {
        return Stream.of(
                Arguments.of(URL_DESPESAS),
                Arguments.of(URL_RECEITAS)
        );
    }
}

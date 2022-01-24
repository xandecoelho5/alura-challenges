package com.alura.challengeback2.exception;

public class RegistroComDescricaoIgualNoMesmoMesException extends RuntimeException {

    public RegistroComDescricaoIgualNoMesmoMesException(String nomeEntidade) {
        super("Já existe uma " + nomeEntidade + " com essa descrição para este mês");
    }
}

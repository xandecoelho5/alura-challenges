package com.alura.challengeback2.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String nomeEntidade, Long id) {
        super(nomeEntidade + " com código " + id + " não existe!");
    }
}

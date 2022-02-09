package com.alura.challengeback2.config.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UsuarioDto {

    @NotEmpty @NotNull
    private String nome;

    @NotEmpty @NotNull
    private String email;

    @NotEmpty @NotNull
    private String senha;
}

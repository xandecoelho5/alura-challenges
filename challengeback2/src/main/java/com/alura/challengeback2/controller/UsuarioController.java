package com.alura.challengeback2.controller;

import com.alura.challengeback2.config.dto.LoginDto;
import com.alura.challengeback2.config.dto.TokenDto;
import com.alura.challengeback2.config.dto.UsuarioDto;
import com.alura.challengeback2.config.service.TokenService;
import com.alura.challengeback2.config.service.UsuarioService;
import com.alura.challengeback2.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Profile("default")
public class UsuarioController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginDto loginDto) {
        UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();

        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.salvar(usuarioDto));
    }
}

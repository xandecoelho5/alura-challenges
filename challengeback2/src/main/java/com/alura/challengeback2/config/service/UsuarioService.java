package com.alura.challengeback2.config.service;

import com.alura.challengeback2.config.dto.UsuarioDto;
import com.alura.challengeback2.model.Usuario;
import com.alura.challengeback2.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(email);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }

    public Usuario salvar(UsuarioDto usuarioDto) {
        String encoded = new BCryptPasswordEncoder().encode(usuarioDto.getSenha());
        usuarioDto.setSenha(encoded);
        Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
        return repository.save(usuario);
    }
}

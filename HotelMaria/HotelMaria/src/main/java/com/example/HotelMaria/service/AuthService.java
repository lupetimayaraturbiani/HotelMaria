package com.example.HotelMaria.service;

import com.example.HotelMaria.dto.AutenticacaoDTO;
import com.example.HotelMaria.factory.UsuarioFactory;
import com.example.HotelMaria.model.Usuario;
import com.example.HotelMaria.model.UsuarioAbstrato;
import com.example.HotelMaria.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario cadastrar(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepo.save(usuario);
    }

    /**
     * Método de autenticação que retorna Optional<Usuario>
     * para manter compatibilidade com código existente
     */
    public Optional<Usuario> autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByEmail(email);
        if (usuarioOpt.isPresent() && encoder.matches(senha, usuarioOpt.get().getSenha())) {
            return usuarioOpt;
        }
        return Optional.empty();
    }

    // Compatibilidade para dados de teste: aceita senha em texto simples
    // Caso a senha armazenada não esteja no formato BCrypt, compara diretamente
    // Isso permite popular `data.sql` com senhas em texto para facilitar testes locais
    public Optional<Usuario> autenticarComFallback(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepo.findByEmail(email);
        if (usuarioOpt.isEmpty()) return Optional.empty();

        Usuario usuario = usuarioOpt.get();
        String stored = usuario.getSenha();
        boolean matched = false;

        if (stored != null) {
            if (stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$")) {
                matched = encoder.matches(senha, stored);
            } else {
                matched = senha.equals(stored);
            }
        }

        return matched ? usuarioOpt : Optional.empty();
    }

    /**
     * Método que usa o Factory Method para criar o tipo de usuário apropriado
     * Retorna DTO com informações de autenticação
     */
    public AutenticacaoDTO autenticarComFactory(String email, String senha) {
        Optional<Usuario> usuarioOpt = autenticarComFallback(email, senha);
        
        if (usuarioOpt.isEmpty()) {
            return new AutenticacaoDTO(false, "E-mail ou senha incorretos");
        }

        // Usa o Factory Method para criar a instância apropriada
        UsuarioAbstrato usuarioAbstrato = UsuarioFactory.criarUsuario(usuarioOpt.get());
        
        return new AutenticacaoDTO(
            true,
            usuarioAbstrato.getTipo(),
            usuarioAbstrato.getPaginaPrincipal()
        );
    }
}

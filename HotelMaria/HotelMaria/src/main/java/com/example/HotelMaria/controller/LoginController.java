package com.example.HotelMaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HotelMaria.dto.AutenticacaoDTO;
import com.example.HotelMaria.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Endpoint de autenticação que usa o Factory Method Pattern
     * Retorna JSON com tipo de usuário e página de destino
     */
    @PostMapping("/login")
    public ResponseEntity<AutenticacaoDTO> autenticar(@RequestParam String email,
                                                       @RequestParam String senha,
                                                       HttpSession session) {
        
        // Usa o AuthService com Factory Method para autenticar
        AutenticacaoDTO autenticacao = authService.autenticarComFactory(email, senha);
        
        if (!autenticacao.isSucesso()) {
            return ResponseEntity.status(401).body(autenticacao);
        }

        // Busca o usuário para armazenar na sessão (usa fallback para aceitar dados de teste)
        var usuarioOpt = authService.autenticarComFallback(email, senha);
        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuarioLogado", usuarioOpt.get());
        }

        return ResponseEntity.ok(autenticacao);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

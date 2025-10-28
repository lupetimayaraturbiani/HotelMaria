package com.example.HotelMaria.controller;

import com.example.HotelMaria.model.Usuario;
import com.example.HotelMaria.repository.UsuarioRepository;
import com.example.HotelMaria.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class AuthController {

    private final UsuarioRepository usuarioRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/cadastro")
    public String telaCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario, Model model) {
        usuario.setSenha(encoder.encode(usuario.getSenha())); // ou senhaHash se manteve o nome antigo
        usuarioRepo.save(usuario);
        model.addAttribute("mensagem", "Cadastro realizado com sucesso!");
        return "login";
    }

    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha, HttpSession session, Model model) {
        var usuarioOpt = usuarioRepo.findByEmail(email);
        if (usuarioOpt.isPresent() && encoder.matches(senha, usuarioOpt.get().getSenha())) {
            session.setAttribute("usuarioLogado", usuarioOpt.get());
            return "redirect:/";
        } else {
            model.addAttribute("erro", "E-mail ou senha incorretos.");
            return "login";
        }
    }
}

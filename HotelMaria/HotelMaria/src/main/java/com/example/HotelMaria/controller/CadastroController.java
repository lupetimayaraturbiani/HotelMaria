package com.example.HotelMaria.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.HotelMaria.model.Usuario;
import com.example.HotelMaria.service.AuthService;

@Controller
public class CadastroController {

    private final AuthService authService;

    public CadastroController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/cadastro")
    public String formularioCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro"; // cadastro.html
    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario,
                            @RequestParam("confirmarSenha") String confirmarSenha,
                            Model model) {

        if (!usuario.getSenha().equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem!");
            return "cadastro";
        }

        authService.cadastrar(usuario);
        return "redirect:/login";
    }
}


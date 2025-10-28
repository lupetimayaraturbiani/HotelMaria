package com.example.HotelMaria.controller;

import com.example.HotelMaria.model.Usuario;
import com.example.HotelMaria.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepo;

    public UsuarioController(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping
    public String listar(Model model) {
        List<Usuario> usuarios = usuarioRepo.findAll();
        model.addAttribute("usuarios", usuarios);
        return "usuarios"; // templates/usuarios.html
    }

    @GetMapping("/perfil")
    public String perfil(HttpSession session, Model model) {
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
        if (logado == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", logado);
        return "perfil"; // templates/perfil.html
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Usuario usuarioAtualizado, HttpSession session) {
        Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
        if (logado == null) {
            return "redirect:/login";
        }
        logado.setNome(usuarioAtualizado.getNome());
        logado.setTelefone(usuarioAtualizado.getTelefone());
        logado.setEndereco(usuarioAtualizado.getEndereco());
        usuarioRepo.save(logado);
        session.setAttribute("usuarioLogado", logado);
        return "redirect:/usuarios/perfil";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        usuarioRepo.deleteById(id);
        return "redirect:/usuarios";
    }
}

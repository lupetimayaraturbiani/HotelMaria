package com.example.HotelMaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.HotelMaria.service.PetService;

@Controller
public class HomeController {

    private final PetService petService;

    public HomeController(PetService petService) {
        this.petService = petService;
    }


    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("pets", petService.listarTodos());
        model.addAttribute("disponiveis", petService.listarDisponiveis());
        return "index"; // index.html
    }


    @GetMapping("/doar")
    public String doar() {
        return "doar";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }

    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }

    @GetMapping("/pagamento")
    public String pagamento() {
        return "pagamento";
    }

    @GetMapping("/index-adm")
    public String indexAdm(jakarta.servlet.http.HttpSession session) {
        var usuarioObj = session.getAttribute("usuarioLogado");
        if (usuarioObj == null) {
            return "redirect:/login";
        }

        try {
            com.example.HotelMaria.model.Usuario usuario = (com.example.HotelMaria.model.Usuario) usuarioObj;
            if (usuario.isAdmin()) {
                return "indexAdm";
            } else {
                return "redirect:/home"; // usuário logado, mas não é admin
            }
        } catch (ClassCastException e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/cadastro-pet")
    public String cadastroPets() {
        return "cadastro-pets";
    }

    @GetMapping("/gereciar-adocao")
    public String gereciarAdocao() {
        return "gereciar-adocao";
    }

    @GetMapping("/gerenciar-tutores")
    public String gerenciarTutores() {
        return "gerenciar-tutores";
    }

    @GetMapping("/perfil-pet/{id}")
    public String perfilPet(@org.springframework.web.bind.annotation.PathVariable Long id, org.springframework.ui.Model model) {
        var pet = petService.buscarPorId(id);
        if (pet == null) {
            return "redirect:/home"; // pet não encontrado
        }
        model.addAttribute("pet", pet);
        return "perfil-pet";
    }
}

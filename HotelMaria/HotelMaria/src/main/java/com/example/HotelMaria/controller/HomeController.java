package com.example.HotelMaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.HotelMaria.repository.AdocaoRepository;
import com.example.HotelMaria.repository.UsuarioRepository;
import com.example.HotelMaria.service.PetService;

@Controller
public class HomeController {

    private final PetService petService;
    private final UsuarioRepository usuarioRepo;
    private final AdocaoRepository adocaoRepo;

    public HomeController(PetService petService, UsuarioRepository usuarioRepo, AdocaoRepository adocaoRepo) {
        this.petService = petService;
        this.usuarioRepo = usuarioRepo;
        this.adocaoRepo = adocaoRepo;
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

    @GetMapping("/gerenciar-adocao")
    public String gereciarAdocao(Model model) {
        model.addAttribute("adocoes", adocaoRepo.findAll());
        return "gerenciar-adocao";
    }

    @GetMapping("/gerenciar-tutores")
    public String gerenciarTutores(Model model) {
        model.addAttribute("tutores", usuarioRepo.findByIsAdminFalse());
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

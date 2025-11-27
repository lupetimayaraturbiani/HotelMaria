package com.example.HotelMaria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelMaria.model.Adocao;
import com.example.HotelMaria.model.Pet;
import com.example.HotelMaria.model.Usuario;
import com.example.HotelMaria.repository.PetRepository;
import com.example.HotelMaria.repository.UsuarioRepository;
import com.example.HotelMaria.service.AdocaoService;

@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController {

    private final AdocaoService adocaoService;
    private final UsuarioRepository usuarioRepo;
    private final PetRepository petRepo;

    public AdocaoController(AdocaoService adocaoService, UsuarioRepository usuarioRepo, PetRepository petRepo) {
        this.adocaoService = adocaoService;
        this.usuarioRepo = usuarioRepo;
        this.petRepo = petRepo;
    }

    @GetMapping
    public List<Adocao> listar() {
        return adocaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adocao> buscar(@PathVariable Long id) {
        Optional<Adocao> a = adocaoService.buscarPorId(id);
        return a.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public static class CreateAdocaoDTO {
        public Long usuarioId;
        public Long petId;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CreateAdocaoDTO dto) {
        if (dto == null || dto.usuarioId == null || dto.petId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuarioId and petId are required");
        }
        Optional<Usuario> u = usuarioRepo.findById(dto.usuarioId);
        Optional<Pet> p = petRepo.findById(dto.petId);
        if (u.isEmpty() || p.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario or pet not found");
        }
        Adocao ad = new Adocao();
        ad.setUsuario(u.get());
        ad.setPet(p.get());
        Adocao saved = adocaoService.salvar(ad);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    public static class StatusDTO {
        public String status;
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestBody StatusDTO dto) {
        if (dto == null || dto.status == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("status is required");
        }
        try {
            Adocao.StatusAdocao novo = Adocao.StatusAdocao.valueOf(dto.status.toUpperCase());
            Adocao updated = adocaoService.atualizarStatus(id, novo);
            if (updated == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid status");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        Optional<Adocao> a = adocaoService.buscarPorId(id);
        if (a.isEmpty()) return ResponseEntity.notFound().build();
        adocaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

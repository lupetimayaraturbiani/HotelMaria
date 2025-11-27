package com.example.HotelMaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.HotelMaria.model.Adocao;
import com.example.HotelMaria.repository.AdocaoRepository;

@Service
public class AdocaoService {

    private final AdocaoRepository adocaoRepo;

    public AdocaoService(AdocaoRepository adocaoRepo) {
        this.adocaoRepo = adocaoRepo;
    }

    public List<Adocao> listarTodos() {
        return adocaoRepo.findAll();
    }

    public Optional<Adocao> buscarPorId(Long id) {
        return adocaoRepo.findById(id);
    }

    public Adocao salvar(Adocao adocao) {
        return adocaoRepo.save(adocao);
    }

    public void deletar(Long id) {
        adocaoRepo.deleteById(id);
    }

    public Adocao atualizarStatus(Long id, Adocao.StatusAdocao novoStatus) {
        var found = adocaoRepo.findById(id);
        if (found.isPresent()) {
            Adocao a = found.get();
            a.setStatus(novoStatus);
            return adocaoRepo.save(a);
        }
        return null;
    }
}

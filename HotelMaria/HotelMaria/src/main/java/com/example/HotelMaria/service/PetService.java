package com.example.HotelMaria.service;

import com.example.HotelMaria.model.Pet;
import com.example.HotelMaria.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> listarTodos() {
        return petRepository.findAll();
    }

    public List<Pet> listarDisponiveis() {
        return petRepository.findByAdotadoFalse();
    }

    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet buscarPorId(Long id) {
        return petRepository.findById(id).orElse(null);
    }
}

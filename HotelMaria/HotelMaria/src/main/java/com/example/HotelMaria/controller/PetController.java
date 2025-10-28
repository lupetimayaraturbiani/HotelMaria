package com.example.HotelMaria.controller;

import org.springframework.web.bind.annotation.*;
import com.example.HotelMaria.repository.PetRepository;
import com.example.HotelMaria.model.Pet;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetRepository petRepo;
    public PetController(PetRepository petRepo) {
        this.petRepo = petRepo;
    }

    @GetMapping
    public List<Pet> list(){
        return petRepo.findAll();
    }

    @GetMapping("/available")
    public List<Pet> available(){
        return petRepo.findByAdotadoFalse();
    }

    @PostMapping
    public Pet create(@RequestBody Pet pet){
        return petRepo.save(pet);
    }

    @PutMapping("/{id}")
    public Pet update(@PathVariable Long id, @RequestBody Pet pet){
        pet.setId(id);
        return petRepo.save(pet);
    }
}

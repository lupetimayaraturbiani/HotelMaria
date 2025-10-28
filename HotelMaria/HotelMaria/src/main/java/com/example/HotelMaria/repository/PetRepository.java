package com.example.HotelMaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.HotelMaria.model.Pet;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAdotadoFalse();
}

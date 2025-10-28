package com.example.HotelMaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.HotelMaria.model.Adocao;
import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    List<Adocao> findByStatus(Adocao.StatusAdocao status);
}

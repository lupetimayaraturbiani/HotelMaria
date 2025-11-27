package com.example.HotelMaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelMaria.model.Usuario;

public interface UsuarioRepository extends  JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    // retorna somente usuários que não são administradores
    List<Usuario> findByIsAdminFalse();
}

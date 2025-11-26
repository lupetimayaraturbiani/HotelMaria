package com.example.HotelMaria.factory;

import com.example.HotelMaria.model.Usuario;
import com.example.HotelMaria.model.UsuarioAbstrato;
import com.example.HotelMaria.model.UsuarioAdmin;
import com.example.HotelMaria.model.UsuarioNormal;

/**
 * Classe que implementa o padrão Factory Method
 * Responsável por criar instâncias de diferentes tipos de usuários
 * baseado no tipo de usuário (Admin ou Normal)
 */
public class UsuarioFactory {

    /**
     * Factory Method que cria o tipo de usuário apropriado
     * 
     * @param usuario Usuario Entity vindo do banco de dados
     * @return UsuarioAbstrato (UsuarioAdmin ou UsuarioNormal)
     */
    public static UsuarioAbstrato criarUsuario(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        // Verifica se o usuário é admin e cria a instância apropriada
        if (usuario.isAdmin()) {
            return new UsuarioAdmin(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTelefone(),
                usuario.getEndereco()
            );
        } else {
            return new UsuarioNormal(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getTelefone(),
                usuario.getEndereco()
            );
        }
    }

    /**
     * Método auxiliar que retorna o tipo de usuário sem criar instância
     * Útil para logs e auditoria
     */
    public static String obterTipoUsuario(Usuario usuario) {
        return usuario != null && usuario.isAdmin() ? "ADMIN" : "NORMAL";
    }
}

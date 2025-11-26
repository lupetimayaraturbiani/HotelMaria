package com.example.HotelMaria.model;

/**
 * Classe concreta que representa um Usuário Administrador
 * Implementa comportamentos específicos do admin
 */
public class UsuarioAdmin extends UsuarioAbstrato {

    public UsuarioAdmin(Long id, String nome, String email, String senha,
                        String telefone, String endereco) {
        super(id, nome, email, senha, telefone, endereco, true);
    }

    @Override
    public String getTipo() {
        return "ADMIN";
    }

    @Override
    public String getPaginaPrincipal() {
        return "/index-adm";
    }

    public void gerarRelatorio() {
        System.out.println("Admin " + this.nome + " gerando relatório...");
    }

    public void gerenciarUsuarios() {
        System.out.println("Admin " + this.nome + " gerenciando usuários...");
    }

    public void deletarPet(Long petId) {
        System.out.println("Admin " + this.nome + " deletando pet com ID: " + petId);
    }
}

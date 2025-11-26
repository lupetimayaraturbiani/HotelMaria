package com.example.HotelMaria.model;

/**
 * Classe concreta que representa um Usuário Normal (Regular)
 * Implementa comportamentos específicos do usuário comum
 */
public class UsuarioNormal extends UsuarioAbstrato {

    public UsuarioNormal(Long id, String nome, String email, String senha,
                         String telefone, String endereco) {
        super(id, nome, email, senha, telefone, endereco, false);
    }

    @Override
    public String getTipo() {
        return "NORMAL";
    }

    @Override
    public String getPaginaPrincipal() {
        return "/home";
    }

    public void adotarPet(Long petId) {
        System.out.println("Usuário " + this.nome + " adotando pet com ID: " + petId);
    }

    public void visualizarPerfil() {
        System.out.println("Usuário " + this.nome + " visualizando seu perfil...");
    }

    public void consultarAnimaisDisponiveis() {
        System.out.println("Usuário " + this.nome + " consultando animais disponíveis...");
    }
}

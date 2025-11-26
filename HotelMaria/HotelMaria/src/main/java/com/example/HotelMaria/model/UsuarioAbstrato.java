package com.example.HotelMaria.model;

/**
 * Classe abstrata que define o contrato para diferentes tipos de usuários
 * Implementa o padrão Factory Method
 */
public abstract class UsuarioAbstrato {
    protected Long id;
    protected String nome;
    protected String email;
    protected String senha;
    protected String telefone;
    protected String endereco;
    protected boolean isAdmin;

    public UsuarioAbstrato(Long id, String nome, String email, String senha, 
                           String telefone, String endereco, boolean isAdmin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
        this.isAdmin = isAdmin;
    }

    /**
     * Método abstrato que define o tipo de usuário
     * Cada subclasse implementará sua própria lógica
     */
    public abstract String getTipo();

    /**
     * Método abstrato para retornar a página de destino após login
     */
    public abstract String getPaginaPrincipal();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

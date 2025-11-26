package com.example.HotelMaria.dto;

/**
 * DTO para retornar informações de autenticação ao frontend
 */
public class AutenticacaoDTO {
    private boolean sucesso;
    private String tipoUsuario;
    private String paginaPrincipal;
    private String mensagem;

    public AutenticacaoDTO() {}

    public AutenticacaoDTO(boolean sucesso, String tipoUsuario, String paginaPrincipal) {
        this.sucesso = sucesso;
        this.tipoUsuario = tipoUsuario;
        this.paginaPrincipal = paginaPrincipal;
    }

    public AutenticacaoDTO(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getPaginaPrincipal() {
        return paginaPrincipal;
    }

    public void setPaginaPrincipal(String paginaPrincipal) {
        this.paginaPrincipal = paginaPrincipal;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

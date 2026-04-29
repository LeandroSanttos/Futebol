package com.example.fut.model.enums;

public enum StatusProposta {
    AGUARDANDO_APROVACAO("aguardando_aprovacao"),
    NAO_APROVADA("nao_aprovada"),
    APROVADA("aprovada");

    private String role;

    StatusProposta(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

package com.example.fut.model.enums;

public enum Posicao {
    GOLEIRO("goleiro"),
    ZAGUEIRO("zagueiro"),
    LATERAL_DIREITO("lateral_direito"),
    LATERAL_ESQUERDO("lateral_esquerdo"),
    VOLANTE("volante"),
    MEIO_CAMPO("meio_campo"),
    ATACANTE("atacante"),
    PONTA_DIREITA("ponta_direita"),
    PONTA_ESQUERDA("ponta_esuqerda");

    private String role;

    Posicao(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

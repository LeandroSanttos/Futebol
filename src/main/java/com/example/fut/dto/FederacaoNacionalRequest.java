package com.example.fut.dto;

import jakarta.validation.constraints.NotBlank;

public record FederacaoNacionalRequest(
        @NotBlank(message = "O nome é obrigatório.")
        String nomeFederacaoNacional,

        @NotBlank(message = "O país sede é obrigatório.")
        String paisSede
) {
}

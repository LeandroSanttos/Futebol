package com.example.fut.dto;

import jakarta.validation.constraints.NotBlank;

public record FederacaoNacionalUpdateRequest(
        @NotBlank(message = "O nome é obrigatório.")
        String nomeFederacao
) {
}

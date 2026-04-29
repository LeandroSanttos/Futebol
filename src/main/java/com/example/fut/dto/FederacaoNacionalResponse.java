package com.example.fut.dto;

import java.util.UUID;

public record FederacaoNacionalResponse(
        UUID id,
        String nomeFederacaoNacional,
        String PaisSede
) {
}

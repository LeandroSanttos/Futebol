package com.example.fut.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AprovarOuReprovarTransferenciaRequest(
        @NotNull (message = "O ID da transferência é obrigatório!")
        UUID idTransferencia,

        @NotNull (message = "A decisão é obrigatória!")
        Boolean decisao
) {
}

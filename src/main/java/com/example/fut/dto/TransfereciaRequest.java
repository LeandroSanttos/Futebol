package com.example.fut.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record TransfereciaRequest(
        @NotBlank (message = "ID do jogador é obrigatório!")
        UUID idJogador,

        @NotBlank (message = "ID do idClube comptador é obrigatório!")
        UUID idClubeComprador,

        @NotNull (message = "Valor da proposta é obrigatório!")
        @Positive (message = "O valor deve ser maior que 0!")
        BigDecimal valorProposta
) {}

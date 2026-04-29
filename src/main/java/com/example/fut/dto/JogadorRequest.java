package com.example.fut.dto;

import com.example.fut.model.enums.Posicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record JogadorRequest(
        @NotBlank (message = "O nome é obrigatório!")
        String nomeJogador,

        @NotBlank (message = "A nacionalidade é obrigatória!")
        String nacionalidade,

        @NotNull (message = "A posição é obrigatória!")
        Posicao posicao,

        UUID idClube,

        @NotNull (message = "O valor é obrigatório!")
        @Positive (message = "O valor deve ser maior que 0!")
        BigDecimal valorJogador
) {
}

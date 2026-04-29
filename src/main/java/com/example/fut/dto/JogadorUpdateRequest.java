package com.example.fut.dto;

import com.example.fut.model.enums.Posicao;
import java.math.BigDecimal;
import java.util.UUID;

public record JogadorUpdateRequest(
        String nomeJogador,
        String nacionalidade,
        Posicao posicao,
        UUID idClube,
        BigDecimal valorJogador
) {
}

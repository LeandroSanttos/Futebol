package com.example.fut.dto;

import com.example.fut.model.Clube;
import com.example.fut.model.enums.Posicao;

public record JogadorDTO(
        String nomeJogador,
        String nacionalidade,
        Posicao posicao,
        Clube clube
) {
}

package com.example.fut.dto;

import com.example.fut.model.Clube;
import com.example.fut.model.enums.Posicao;

import java.math.BigDecimal;
import java.util.UUID;

public record JogadorResponse(
        UUID idJogador,
        String nomeJogador,
        String nacionalidade,
        Posicao posicao,
        Clube clube,
        BigDecimal valorJogador
) {}
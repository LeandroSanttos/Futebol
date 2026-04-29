package com.example.fut.dto;

import com.example.fut.model.enums.StatusProposta;

import java.util.UUID;

public record TransferenciaResponse(
        UUID idTransferencia,
        UUID idJogadorCompra,
        String nomeJogador,
        UUID idClubeVendedor,
        String nomeClubeVendedor,
        UUID idClubeComprador,
        String nomeClubeComprador,
        StatusProposta statusProposta
) {}

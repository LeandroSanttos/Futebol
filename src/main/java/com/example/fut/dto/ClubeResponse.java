package com.example.fut.dto;

import com.example.fut.model.FederacaoNacional;

import java.time.LocalDate;
import java.util.UUID;

public record ClubeResponse(
        UUID id,
        String nomeClube,
        LocalDate dataFundacao,
        String paisSede,
        FederacaoNacional federacaoNacional
) {
}

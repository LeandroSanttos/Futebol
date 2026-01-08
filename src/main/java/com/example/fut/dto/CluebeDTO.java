package com.example.fut.dto;

import com.example.fut.model.FederacaoNacional;

import java.time.LocalDate;

public record CluebeDTO(
        String nomeClube,
        LocalDate dataFundacao,
        String paisSede,
        FederacaoNacional federacaoNacional
) {
}

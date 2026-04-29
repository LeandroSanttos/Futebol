package com.example.fut.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.UUID;

public record ClubeRequest(
        @NotBlank(message = "O nome do idClube é obrigatório.")
        String nomeClube,

        @NotNull(message = "A data de fundação é obrigatória.")
        @Past(message = "A data de fundação deve ser no passado.")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataFundacao,

        @NotBlank(message = "O país sede é obrigatório.")
        String paisSede,

        @NotNull(message = "A federação nacional é obrigatória.")
        UUID idFederacaoNacional
) {
}

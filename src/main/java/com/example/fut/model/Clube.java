package com.example.fut.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name = "CLUBE")
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idClube;
    private String nomeClube;
    private LocalDate dataFundacao;
    private String paisSede;
    private FederacaoNacional federacaoNacional;
}

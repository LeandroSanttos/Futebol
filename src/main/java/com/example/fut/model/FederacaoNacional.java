package com.example.fut.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "FEDERACAO_NACIONAL")
public class FederacaoNacional {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private UUID idFederacaoNacional;
    private String nomeFederacaoNacional;
    private String paisSede;
}

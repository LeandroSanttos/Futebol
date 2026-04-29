package com.example.fut.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table (name = "FEDERACAO_NACIONAL")
public class FederacaoNacional {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column (name = "id_federacao_nacional",unique = true, nullable = false, updatable = false)
    private UUID idFederacaoNacional;

    @Column (name = "nome_federacao_nacional", nullable = false)
    private String nomeFederacaoNacional;

    @Column (name = "pais_sede", nullable = false)
    private String paisSede;
}

package com.example.fut.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table (name = "CLUBE")
public class Clube {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name = "id_clube", nullable = false, updatable = false)
    private UUID idClube;

    @Column ( name = "nome_clube", unique = true, nullable = false)
    private String nomeClube;

    @Column (name = "data_fundacao", nullable = false, updatable = false)
    private LocalDate dataFundacao;

    @Column (name = "pais_sede", nullable = false, updatable = false)
    private String paisSede;

    @OneToMany (mappedBy = "clubeJogador")
    private List<Jogador> jogadores;

    @ManyToOne
    @JoinColumn (name = "id_federacao_nacional")
    private FederacaoNacional federacaoNacional;
}

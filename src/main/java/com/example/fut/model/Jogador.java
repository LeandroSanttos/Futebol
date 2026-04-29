package com.example.fut.model;

import com.example.fut.model.enums.Posicao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table (name = "JOGADOR")
public class Jogador {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column (name = "id_jogador", nullable = false, updatable = false)
    private UUID idJogador;

    @Column (name = "nome_jogador", nullable = false)
    private String nomeJogador;

    @Column (name = "nacionalidade", nullable = false)
    private String nacionalidade;

    @Enumerated (EnumType.STRING)
    @Column (name = "posicao", nullable = false)
    private Posicao posicao;

    @ManyToOne
    @JoinColumn (name = "id_clube", nullable = true)
    private Clube clubeJogador;

    @Column (name = "valor_jogador", nullable = false)
    private BigDecimal valorJogador;
}

package com.example.fut.model;

import com.example.fut.model.enums.Posicao;
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
@Table(name = "JOGADOR")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idJogador;
    private String nomeJogador;
    private String nacionalidade;
    private Posicao posicao;
    private Clube clubeJogador;
}

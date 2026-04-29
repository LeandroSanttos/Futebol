package com.example.fut.repository;

import com.example.fut.model.Clube;
import com.example.fut.model.Jogador;
import com.example.fut.model.enums.Posicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, UUID> {
    List<Jogador> findByNomeJogadorContainingIgnoreCase(String nome);
    List<Jogador> findByPosicao(Posicao posicao);
    List<Jogador> findByClubeJogador(Clube clube);
}

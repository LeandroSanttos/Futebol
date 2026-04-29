package com.example.fut.repository;

import com.example.fut.model.Clube;
import com.example.fut.model.Jogador;
import com.example.fut.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, UUID> {
    /*List<Transferencia> findByJogadorCompra(Jogador idJogador);
    List<Transferencia> findByClube(Clube idClube);*/
}

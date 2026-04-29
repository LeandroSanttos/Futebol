package com.example.fut.service;

import com.example.fut.dto.JogadorRequest;
import com.example.fut.dto.JogadorResponse;
import com.example.fut.dto.JogadorUpdateRequest;
import com.example.fut.exception.ClubeInvalidoException;
import com.example.fut.exception.JogadorInvalidoException;
import com.example.fut.mapper.JogadorMapper;
import com.example.fut.model.Clube;
import com.example.fut.model.Jogador;
import com.example.fut.model.enums.Posicao;
import com.example.fut.repository.ClubeRepository;
import com.example.fut.repository.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JogadorService {
    private final JogadorRepository jogadorRepository;
    private final JogadorMapper jogadorMapper;
    private final ClubeRepository clubeRepository;

    public JogadorService(JogadorRepository jogadorRepository, JogadorMapper jogadorMapper, ClubeRepository clubeRepository) {
        this.jogadorRepository = jogadorRepository;
        this.jogadorMapper = jogadorMapper;
        this.clubeRepository = clubeRepository;
    }

    public JogadorResponse salvarJogador(JogadorRequest request) {
        Jogador jogador = jogadorMapper.toEntity(request);
        if (request.idClube() != null) {
            Clube clube = clubeRepository.findById(request.idClube())
                            .orElseThrow(() -> new ClubeInvalidoException("Clube com o ID: " + request.idClube() + " não encontrado!"));
            jogador.setClubeJogador(clube);
        } else {
            jogador.setClubeJogador(null);
        }
        jogadorRepository.save(jogador);

        return jogadorMapper.toDTO(jogador);
    }

    public JogadorResponse buscarJogadorPorID(UUID idJogador) {
        return jogadorMapper.toDTO(jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new JogadorInvalidoException("Jogador com o ID: " + idJogador + " não encontrado!")));
    }

    public List<JogadorResponse> buscarJogadorPorNome(String nomeJogador) {
        return jogadorRepository.findByNomeJogadorContainingIgnoreCase(nomeJogador)
                .stream()
                .map(jogadorMapper::toDTO)
                .toList();
    }

    public List<JogadorResponse> buscarJogadorPorPosicao(Posicao posicaoJogador) {
        return jogadorRepository.findByPosicao(posicaoJogador)
                .stream()
                .map(jogadorMapper::toDTO)
                .toList();
    }

    public List<JogadorResponse> buscarJogadorPorClube(UUID idClubeJogador) {
        Clube clubeJogador = clubeRepository.findById(idClubeJogador)
                .orElseThrow(() -> new ClubeInvalidoException("Clube com o ID: " + idClubeJogador + " não encontrado!"));

        return jogadorRepository.findByClubeJogador(clubeJogador)
                .stream()
                .map(jogadorMapper::toDTO)
                .toList();
    }

    public List<JogadorResponse> buscarTodosJogadores() {
        return jogadorRepository.findAll().stream().map(jogadorMapper::toDTO).toList();
    }

    public JogadorResponse atualizarJogador(UUID idJogador, JogadorUpdateRequest request) {
        Jogador jogadorAtalizado = jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new JogadorInvalidoException("Jogador com o ID: " + idJogador + " não encontrado!"));
        jogadorMapper.updateEntityFromRequest(request, jogadorAtalizado);
        jogadorRepository.save(jogadorAtalizado);

        return jogadorMapper.toDTO(jogadorAtalizado);
    }

    public void excluirJogador(UUID idJogador) {
        if (!jogadorRepository.existsById(idJogador)) {
            throw new JogadorInvalidoException("Jogador com o ID: " + idJogador + " não encontrado!");
        }

        jogadorRepository.deleteById(idJogador);
    }
}

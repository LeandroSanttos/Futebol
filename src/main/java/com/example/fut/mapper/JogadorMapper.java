package com.example.fut.mapper;

import com.example.fut.dto.JogadorRequest;
import com.example.fut.dto.JogadorResponse;
import com.example.fut.dto.JogadorUpdateRequest;
import com.example.fut.model.Jogador;
import org.springframework.stereotype.Component;

@Component
public class JogadorMapper {

    public Jogador toEntity(JogadorRequest request) {
        if (request == null) {
            return null;
        }

        Jogador jogador = new Jogador();
        jogador.setNomeJogador(request.nomeJogador());
        jogador.setNacionalidade(request.nacionalidade());
        jogador.setPosicao(request.posicao());
        jogador.setValorJogador(request.valorJogador());

        return jogador;
    }

    public JogadorResponse toDTO(Jogador jogador) {
        if (jogador == null) {
            return null;
        }

        return new JogadorResponse(
                jogador.getIdJogador(),
                jogador.getNomeJogador(),
                jogador.getNacionalidade(),
                jogador.getPosicao(),
                jogador.getClubeJogador(),
                jogador.getValorJogador()
        );
    }

    public void updateEntityFromRequest(JogadorUpdateRequest request, Jogador jogador) {
        if (request == null || jogador == null) {
            return;
        }

        if (request.nomeJogador() != null) jogador.setNomeJogador(request.nomeJogador());
        if (request.nacionalidade() != null) jogador.setNacionalidade(request.nacionalidade());
        if (request.posicao() != null) jogador.setPosicao(request.posicao());
        if (request.valorJogador() != null) jogador.setValorJogador(request.valorJogador());
    }
}

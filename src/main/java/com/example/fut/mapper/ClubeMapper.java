package com.example.fut.mapper;

import com.example.fut.dto.ClubeRequest;
import com.example.fut.dto.ClubeResponse;
import com.example.fut.dto.ClubeUpdateRequest;
import com.example.fut.model.Clube;
import org.springframework.stereotype.Component;

@Component
public class ClubeMapper {

    public Clube toEntity(ClubeRequest request) {
        if (request == null) {
            return null;
        }

        Clube clube = new Clube();
        clube.setNomeClube(request.nomeClube());
        clube.setDataFundacao(request.dataFundacao());
        clube.setPaisSede(request.paisSede());

        return clube;
    }

    public ClubeResponse toDTO(Clube clube) {
        if (clube == null) {
            return null;
        }

        return new ClubeResponse(
                clube.getIdClube(),
                clube.getNomeClube(),
                clube.getDataFundacao(),
                clube.getPaisSede(),
                clube.getFederacaoNacional()
        );
    }

    public void updateEntityFromRequest(ClubeUpdateRequest request, Clube clube) {
        if (request == null || clube == null) {
            return;
        }

        if (request.nomeClube() != null) clube.setNomeClube(request.nomeClube());
    }
}

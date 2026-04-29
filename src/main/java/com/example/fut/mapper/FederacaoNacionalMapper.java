package com.example.fut.mapper;

import com.example.fut.dto.FederacaoNacionalRequest;
import com.example.fut.dto.FederacaoNacionalResponse;
import com.example.fut.dto.FederacaoNacionalUpdateRequest;
import com.example.fut.model.FederacaoNacional;
import org.springframework.stereotype.Component;

@Component
public class FederacaoNacionalMapper {

    public FederacaoNacional toEntity(FederacaoNacionalRequest request) {
        if (request == null) {
            return null;
        }

        FederacaoNacional federacaoNacional = new FederacaoNacional();
        federacaoNacional.setNomeFederacaoNacional(request.nomeFederacaoNacional());
        federacaoNacional.setPaisSede(request.paisSede());

        return federacaoNacional;
    }

    public FederacaoNacionalResponse toDTO(FederacaoNacional federacaoNacional) {
        return new FederacaoNacionalResponse(
                federacaoNacional.getIdFederacaoNacional(),
                federacaoNacional.getNomeFederacaoNacional(),
                federacaoNacional.getPaisSede()
        );
    }

    public void updateEntityFromRequest(FederacaoNacionalUpdateRequest request, FederacaoNacional federacaoNacional) {
        if (request == null || federacaoNacional == null) {
            return;
        }

        if (request.nomeFederacao() != null) federacaoNacional.setNomeFederacaoNacional(request.nomeFederacao());
    }
}

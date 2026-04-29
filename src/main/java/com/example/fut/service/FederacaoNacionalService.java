package com.example.fut.service;

import com.example.fut.dto.FederacaoNacionalRequest;
import com.example.fut.dto.FederacaoNacionalResponse;
import com.example.fut.dto.FederacaoNacionalUpdateRequest;
import com.example.fut.exception.FederacaoNacionalInvalidaException;
import com.example.fut.mapper.FederacaoNacionalMapper;
import com.example.fut.model.FederacaoNacional;
import com.example.fut.repository.FederacaoNacionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FederacaoNacionalService {

    private final FederacaoNacionalRepository federacaoNacionalRepository;
    private final FederacaoNacionalMapper federacaoNacionalMapper;

    public FederacaoNacionalService(FederacaoNacionalRepository federacaoNacionalRepository, FederacaoNacionalMapper federacaoNacionalMapper) {
        this.federacaoNacionalRepository = federacaoNacionalRepository;
        this.federacaoNacionalMapper = federacaoNacionalMapper;
    }

    public FederacaoNacionalResponse salvarFederacaoNacional(FederacaoNacionalRequest request) {
        FederacaoNacional federacaoNacional = federacaoNacionalMapper.toEntity(request);
        federacaoNacionalRepository.save(federacaoNacional);

        return federacaoNacionalMapper.toDTO(federacaoNacional);
    }

    public FederacaoNacionalResponse buscarFederacaoNacionalPorId(UUID idFederacaoNacional) {
        return federacaoNacionalMapper.toDTO(federacaoNacionalRepository.findById(idFederacaoNacional)
                .orElseThrow(() -> new FederacaoNacionalInvalidaException("Federação Nacional com ID: " +
                        idFederacaoNacional + " não encontrada!")));
    }

    public List<FederacaoNacionalResponse> buscarFederacaoNacionalPorNome(String nomeFederacaoNacional) {
        return federacaoNacionalRepository.findByNomeFederacaoNacionalContainingIgnoreCase(nomeFederacaoNacional)
                .stream()
                .map(federacaoNacionalMapper::toDTO)
                .toList();
    }

    public List<FederacaoNacionalResponse> buscarTodasFederacoesNacionais() {
        return federacaoNacionalRepository.findAll().stream().map(federacaoNacionalMapper::toDTO).toList();
    }

    public FederacaoNacionalResponse atualizarFederacaoNacional(UUID idFederacaoNacional, FederacaoNacionalUpdateRequest request) {
        FederacaoNacional federacaoNacionalAtualizada = federacaoNacionalRepository.findById(idFederacaoNacional)
                .orElseThrow(() -> new FederacaoNacionalInvalidaException("Federação Nacional com ID: "
                        + idFederacaoNacional + " não encontrada!"));
        federacaoNacionalMapper.updateEntityFromRequest(request, federacaoNacionalAtualizada);
        federacaoNacionalRepository.save(federacaoNacionalAtualizada);

        return federacaoNacionalMapper.toDTO(federacaoNacionalAtualizada);
    }

    public void excluirFederacaoNacional(UUID idFederacaoNacional) {
        if (!federacaoNacionalRepository.existsById(idFederacaoNacional)) {
            throw new FederacaoNacionalInvalidaException("Federação Nacional com ID: " + idFederacaoNacional + " não encontrada!");
        }

        federacaoNacionalRepository.deleteById(idFederacaoNacional);
    }
}

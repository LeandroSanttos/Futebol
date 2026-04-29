package com.example.fut.service;

import com.example.fut.dto.ClubeRequest;
import com.example.fut.dto.ClubeResponse;
import com.example.fut.dto.ClubeUpdateRequest;
import com.example.fut.exception.ClubeInvalidoException;
import com.example.fut.exception.FederacaoNacionalInvalidaException;
import com.example.fut.mapper.ClubeMapper;
import com.example.fut.model.Clube;
import com.example.fut.model.FederacaoNacional;
import com.example.fut.repository.ClubeRepository;
import com.example.fut.repository.FederacaoNacionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClubeService {

    private final ClubeRepository clubeRepository;
    private final ClubeMapper clubeMapper;
    private final FederacaoNacionalRepository federacaoNacionalRepository;

    public ClubeService(ClubeRepository clubeRepository, ClubeMapper clubeMapper, FederacaoNacionalRepository federacaoNacionalRepository) {
        this.clubeRepository = clubeRepository;
        this.clubeMapper = clubeMapper;
        this.federacaoNacionalRepository = federacaoNacionalRepository;
    }

    public ClubeResponse salvarClube(ClubeRequest request) {
        FederacaoNacional federacaoNacional = federacaoNacionalRepository.findById(request.idFederacaoNacional())
                .orElseThrow(() -> new FederacaoNacionalInvalidaException("Federação com o ID: " +
                        request.idFederacaoNacional() + " não encontrada!"));

        Clube clube = clubeMapper.toEntity(request);
        clube.setFederacaoNacional(federacaoNacional);
        clubeRepository.save(clube);

        return clubeMapper.toDTO(clube);
    }

    public ClubeResponse buscarClubePorId(UUID idClube) {
        return clubeMapper.toDTO(clubeRepository.findById(idClube)
                .orElseThrow(() -> new ClubeInvalidoException("Clube com o ID: " + idClube + " não encontrado!")));
    }

    public List<ClubeResponse> buscarClubePorNome(String nomeClube) {
        return clubeRepository.findByNomeClubeContainingIgnoreCase(nomeClube)
                .stream()
                .map(clubeMapper::toDTO)
                .toList();
    }

    public List<ClubeResponse> buscarPorFederacaoNacional(UUID idFederacaoNacionalId) {
        FederacaoNacional federacaoNacional = federacaoNacionalRepository.findById(idFederacaoNacionalId)
                .orElseThrow(() -> new FederacaoNacionalInvalidaException("Federação com o ID: " + idFederacaoNacionalId + " não encontrada!"));

        return clubeRepository.findByFederacaoNacional(federacaoNacional)
                .stream()
                .map(clubeMapper::toDTO)
                .toList();
    }

    public List<ClubeResponse> buscarTodosClubes() {
        return clubeRepository.findAll().stream().map(clubeMapper::toDTO).toList();
    }

    public ClubeResponse atualizarClube(UUID idClube, ClubeUpdateRequest request) {
        Clube clubeAtualizado = clubeRepository.findById(idClube)
                .orElseThrow(() -> new ClubeInvalidoException("Clube com o ID: " + idClube + " não encontrado!"));
        clubeMapper.updateEntityFromRequest(request, clubeAtualizado);

        return clubeMapper.toDTO(clubeRepository.save(clubeAtualizado));
    }

    public void excluirClube(UUID idClube) {
        if (!clubeRepository.existsById(idClube)) {
            throw new ClubeInvalidoException("Clube com o ID: " + idClube + " não encontrado!");
        }

        clubeRepository.deleteById(idClube);
    }
}

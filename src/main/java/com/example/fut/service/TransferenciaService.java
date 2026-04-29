package com.example.fut.service;

import com.example.fut.dto.TransfereciaRequest;
import com.example.fut.dto.TransferenciaResponse;
import com.example.fut.exception.ClubeInvalidoException;
import com.example.fut.exception.JogadorInvalidoException;
import com.example.fut.exception.TransferenciaException;
import com.example.fut.mapper.TransferenciaMapper;
import com.example.fut.model.Clube;
import com.example.fut.model.Jogador;
import com.example.fut.model.Transferencia;
import com.example.fut.model.enums.StatusProposta;
import com.example.fut.repository.ClubeRepository;
import com.example.fut.repository.JogadorRepository;
import com.example.fut.repository.TransferenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final TransferenciaMapper transferenciaMapper;
    private final JogadorRepository jogadorRepository;
    private final ClubeRepository clubeRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, TransferenciaMapper transferenciaMapper,
                                JogadorRepository jogadorRepository, ClubeRepository clubeRepository) {
        this.transferenciaRepository = transferenciaRepository;
        this.transferenciaMapper = transferenciaMapper;
        this.jogadorRepository = jogadorRepository;
        this.clubeRepository = clubeRepository;
    }

    public TransferenciaResponse registrarPropostaTransferencia(TransfereciaRequest request) {
        Jogador jogador = jogadorRepository.findById(request.idJogador())
                .orElseThrow(() -> new JogadorInvalidoException("Jogador com ID: " + request.idJogador() + " não encontrado!"));

        Clube clubeVendedor = jogador.getClubeJogador();

        Clube clubeComprador = clubeRepository.findById(request.idClubeComprador())
                .orElseThrow(() -> new ClubeInvalidoException("Clube com ID: " + request.idClubeComprador() + " não encontrado!"));

        BigDecimal valorProposta = request.valorProposta();

        if (clubeVendedor.getIdClube().equals(clubeComprador.getIdClube())) {
            throw new TransferenciaException("Não pode vender o jogador para o mesmo clube!");
        }

        Transferencia transferencia = new Transferencia();
        transferencia.setJogadorCompra(jogador);
        transferencia.setClubeVendedor(clubeVendedor);
        transferencia.setClubeComprador(clubeComprador);
        transferencia.setValorTransferencia(valorProposta);
        transferencia.setStatusProposta(StatusProposta.AGUARDANDO_APROVACAO);

        transferenciaRepository.save(transferencia);

        return transferenciaMapper.toResponse(transferencia);
    }

    @Transactional
    public TransferenciaResponse aceitarPropostaTransferencia(UUID idTransferencia, Boolean decisao) {
        Transferencia transferencia = transferenciaRepository.findById(idTransferencia)
                .orElseThrow(() -> new TransferenciaException("Transferência com o ID: " + idTransferencia + " não encontrada!"));

        if (!transferencia.getStatusProposta().equals(StatusProposta.AGUARDANDO_APROVACAO)) {
            throw new IllegalStateException("A proposta de Transferência não está no status de 'AGUARDANDO_APROVACAO' para ser aprovada.");
        }

        if (decisao) {
            transferencia.setStatusProposta(StatusProposta.APROVADA);
        } else {
            transferencia.setStatusProposta(StatusProposta.NAO_APROVADA);
        }

        transferenciaRepository.save(transferencia);

        return transferenciaMapper.toResponse(transferencia);
    }

    public TransferenciaResponse buscarTransferenciaPorId(UUID idTransferencia) {
        Transferencia transferencia = transferenciaRepository.findById(idTransferencia)
                .orElseThrow(() -> new TransferenciaException("Transferência com o ID: " + idTransferencia + " não encontrada!"));

        return transferenciaMapper.toResponse(transferencia);
    }

    /*public List<TransferenciaResponse> buscarTransferenciaPorJogador(UUID idJogador) {
        Jogador jogador = jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new JogadorInvalidoException("Jogador com o ID: " + idJogador + " não encontrado!"));

        return transferenciaRepository.findByJogadorCompra(jogador)
                .stream()
                .map(transferenciaMapper::toResponse)
                .toList();
    }

    public List<TransferenciaResponse> buscarTranferenciaPorClube(UUID idClubeVendedor) {
        Clube clube = clubeRepository.findById(idClubeVendedor)
                .orElseThrow(() -> new ClubeInvalidoException("Clube com o ID: " + idClubeVendedor + " não encontrado!"));

        return transferenciaRepository.findByClube(clube)
                .stream()
                .map(transferenciaMapper::toResponse)
                .toList();
    }*/

    public List<TransferenciaResponse> buscarTodasTransferencia() {
        return transferenciaRepository.findAll().stream().map(transferenciaMapper::toResponse).toList();
    }

    public void excluirTransferencia(UUID idTransferencia) {
        Transferencia transferencia = transferenciaRepository.findById(idTransferencia)
                .orElseThrow(() -> new TransferenciaException("Transferência com o ID: " + idTransferencia + " não encontrada!"));

        if (!transferencia.getStatusProposta().equals(StatusProposta.AGUARDANDO_APROVACAO)) {
            throw new TransferenciaException("Não pode exluir uma tranferência que já aconteceu!");
        }

        transferenciaRepository.deleteById(idTransferencia);
    }
}

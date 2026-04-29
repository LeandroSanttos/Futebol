package com.example.fut.controller;

import com.example.fut.dto.AprovarOuReprovarTransferenciaRequest;
import com.example.fut.dto.TransfereciaRequest;
import com.example.fut.dto.TransferenciaResponse;
import com.example.fut.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping("/registro")
    public ResponseEntity<TransferenciaResponse> registrarTransferencia(@RequestBody TransfereciaRequest request) {
        return ResponseEntity.ok(transferenciaService.registrarPropostaTransferencia(request));
    }

    @PatchMapping("/aprovar-reprovar")
    public ResponseEntity<TransferenciaResponse> aprovarTransferencia(@RequestBody AprovarOuReprovarTransferenciaRequest request) {
        return ResponseEntity.ok(transferenciaService.aceitarPropostaTransferencia(request.idTransferencia(), request.decisao()));
    }

    @GetMapping
    public ResponseEntity<List<TransferenciaResponse>> listarTodasTransferencias() {
        return ResponseEntity.ok(transferenciaService.buscarTodasTransferencia());
    }

    @GetMapping("/{idTransferencia}")
    public ResponseEntity<TransferenciaResponse> buscarTransferenciaPorId(@PathVariable UUID idTransferencia) {
        return ResponseEntity.ok(transferenciaService.buscarTransferenciaPorId(idTransferencia));
    }

    /*@GetMapping("/jogador/{idJogador}")
    public ResponseEntity<List<TransferenciaResponse>> listarTransferenciasJogador(@PathVariable UUID idJogador) {
        return ResponseEntity.ok(transferenciaService.buscarTransferenciaPorJogador(idJogador));
    }

    @GetMapping("/clube/{idClube}")
    public ResponseEntity<List<TransferenciaResponse>> listarTransferenciasClube(@PathVariable UUID idClube) {
        return ResponseEntity.ok(transferenciaService.buscarTranferenciaPorClube(idClube));
    }*/

    @DeleteMapping("/{idTransferencia}")
    public ResponseEntity<Void> cancelarTransferencia(@PathVariable UUID idTransferencia) {
        transferenciaService.excluirTransferencia(idTransferencia);

        return ResponseEntity.noContent().build();
    }
}

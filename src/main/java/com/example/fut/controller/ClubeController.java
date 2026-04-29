package com.example.fut.controller;

import com.example.fut.dto.ClubeRequest;
import com.example.fut.dto.ClubeResponse;
import com.example.fut.dto.ClubeUpdateRequest;
import com.example.fut.service.ClubeService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

    private final ClubeService clubeService;

    public ClubeController(ClubeService clubeService) {
        this.clubeService = clubeService;
    }

    @PostMapping("/registro")
    public ResponseEntity<ClubeResponse> resgistrarClube(@RequestBody ClubeRequest request) {
        return ResponseEntity.ok(clubeService.salvarClube(request));
    }

    @GetMapping
    public ResponseEntity<List<ClubeResponse>> listarTodosClubes() {
        return ResponseEntity.ok(clubeService.buscarTodosClubes());
    }

    @GetMapping("/{idClube}")
    public ResponseEntity<ClubeResponse> buscarClubePorId(@PathVariable UUID idClube) {
        return ResponseEntity.ok(clubeService.buscarClubePorId(idClube));
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<ClubeResponse>> buscarClubePorNome
            (@RequestParam @NotBlank (message = "O nome é obrigatório!") String nome) {
        return ResponseEntity.ok(clubeService.buscarClubePorNome(nome));
    }

    @GetMapping("/federacao_nacional/{idFederacaoNacional}")
    public ResponseEntity<List<ClubeResponse>> buscarClubePorFederacaoNacional(@PathVariable UUID idFederacaoNacional) {
        return ResponseEntity.ok(clubeService.buscarPorFederacaoNacional(idFederacaoNacional));
    }

    @PatchMapping("/{idClube}")
    public ResponseEntity<ClubeResponse> atualizarClube(@PathVariable UUID idCLube, @RequestBody ClubeUpdateRequest request) {
        return ResponseEntity.ok(clubeService.atualizarClube(idCLube, request));
    }

    @DeleteMapping("/{idClube}")
    public ResponseEntity<Void> excluirClube(@PathVariable UUID idClube) {
        clubeService.excluirClube(idClube);

        return ResponseEntity.noContent().build();
    }
}

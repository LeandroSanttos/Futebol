package com.example.fut.controller;

import com.example.fut.dto.FederacaoNacionalRequest;
import com.example.fut.dto.FederacaoNacionalResponse;
import com.example.fut.dto.FederacaoNacionalUpdateRequest;
import com.example.fut.service.FederacaoNacionalService;
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
@RequestMapping("/federacoes_nacionais")
public class FederacaoNacionalController {

    private final FederacaoNacionalService federacaoNacionalService;

    public FederacaoNacionalController(FederacaoNacionalService federacaoNacionalService) {
        this.federacaoNacionalService = federacaoNacionalService;
    }

    @PostMapping("/registro")
    public ResponseEntity<FederacaoNacionalResponse> registrarFederacaoNacional(@RequestBody FederacaoNacionalRequest request) {
        return ResponseEntity.ok(federacaoNacionalService.salvarFederacaoNacional(request));
    }

    @GetMapping
    public ResponseEntity<List<FederacaoNacionalResponse>> listarTodasFederacoesNacionais() {
        return ResponseEntity.ok(federacaoNacionalService.buscarTodasFederacoesNacionais());
    }

    @GetMapping("/{idFederacaoNacional}")
    public ResponseEntity<FederacaoNacionalResponse> buscarFederacaoNacionalPorId(@PathVariable UUID idFederacaoNacional) {
        return ResponseEntity.ok(federacaoNacionalService.buscarFederacaoNacionalPorId(idFederacaoNacional));
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<FederacaoNacionalResponse>> listarFederacaoNacionalPorNome
            (@RequestParam @NotBlank(message = "O nome é obrigatório!") String nome) {
        return ResponseEntity.ok(federacaoNacionalService.buscarFederacaoNacionalPorNome(nome));
    }

    @PatchMapping("/{idFederacaoNacional}")
    public ResponseEntity<FederacaoNacionalResponse> atualizarFederacaoNacional
            (@PathVariable UUID idFederacaoNacional, @RequestBody FederacaoNacionalUpdateRequest request) {
        return ResponseEntity.ok(federacaoNacionalService.atualizarFederacaoNacional(idFederacaoNacional, request));
    }

    @DeleteMapping("/{idFederacaoNacional}")
    public ResponseEntity<Void> excluirFederacaoNacional(@PathVariable UUID idFederacaoNacional) {
        federacaoNacionalService.excluirFederacaoNacional(idFederacaoNacional);

        return ResponseEntity.noContent().build();
    }
}

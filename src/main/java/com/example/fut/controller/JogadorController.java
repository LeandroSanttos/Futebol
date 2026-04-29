package com.example.fut.controller;

import com.example.fut.dto.JogadorRequest;
import com.example.fut.dto.JogadorResponse;
import com.example.fut.dto.JogadorUpdateRequest;
import com.example.fut.model.enums.Posicao;
import com.example.fut.service.JogadorService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping("/registro")
    public ResponseEntity<JogadorResponse> registrarJogador(@RequestBody JogadorRequest request) {
        return ResponseEntity.ok(jogadorService.salvarJogador(request));
    }

    @GetMapping
    public ResponseEntity<List<JogadorResponse>> listarTodosJogadores() {
        return ResponseEntity.ok(jogadorService.buscarTodosJogadores());
    }

    @GetMapping("/{idJogador}")
    public ResponseEntity<JogadorResponse> buscarJogadorPorID(@PathVariable UUID idJogador) {
        return ResponseEntity.ok(jogadorService.buscarJogadorPorID(idJogador));
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<JogadorResponse>> listarJogadorPorNome
            (@RequestParam @NotBlank (message = "O nome é obrigatório!") String nome) {
        return ResponseEntity.ok(jogadorService.buscarJogadorPorNome(nome));
    }

    @GetMapping("/posicao")
    public ResponseEntity<List<JogadorResponse>> listarJogadorPorPosicao
            (@RequestParam @NotNull (message = "A posição é obrigatória!") Posicao posicao) {
        return ResponseEntity.ok(jogadorService.buscarJogadorPorPosicao(posicao));
    }

    @GetMapping("/clube/{idClube}")
    public ResponseEntity<List<JogadorResponse>> listarJogadoresPorClube(@PathVariable UUID idClube) {
        return ResponseEntity.ok(jogadorService.buscarJogadorPorClube(idClube));
    }

    @PatchMapping("/{idJogador}")
    public ResponseEntity<JogadorResponse> atualizarJogador(@PathVariable UUID idJogador, @RequestBody JogadorUpdateRequest request) {
        return ResponseEntity.ok(jogadorService.atualizarJogador(idJogador, request));
    }

    @DeleteMapping("{idJogador}")
    public ResponseEntity<Void> excluirJogador(@PathVariable UUID idJogador) {
        jogadorService.excluirJogador(idJogador);

        return ResponseEntity.noContent().build();
    }
}

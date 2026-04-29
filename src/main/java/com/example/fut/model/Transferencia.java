package com.example.fut.model;

import com.example.fut.model.enums.StatusProposta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Transferencia {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column (name = "id_transferencia", nullable = false, updatable = false)
    private UUID idTransferencia;

    @ManyToOne
    @JoinColumn (name = "id_jogador", nullable = false)
    private Jogador jogadorCompra;

    @ManyToOne
    @JoinColumn (name = "id_clube_vendedor", nullable = false)
    private Clube clubeVendedor;

    @ManyToOne
    @JoinColumn (name = "id_clube_comprador", nullable = false)
    private Clube clubeComprador;

    @Column (name = "valor_proposta", nullable = false)
    private BigDecimal valorTransferencia;

    @Enumerated (EnumType.STRING)
    @Column (name = "status_proposta")
    private StatusProposta statusProposta;
}

package com.example.fut.mapper;

import com.example.fut.dto.TransferenciaResponse;
import com.example.fut.model.Transferencia;
import org.springframework.stereotype.Component;

@Component
public class TransferenciaMapper {

    public TransferenciaResponse toResponse(Transferencia transferencia) {

        if (transferencia == null) {
            return null;
        }

        return new TransferenciaResponse(
                transferencia.getIdTransferencia(),

                transferencia.getJogadorCompra().getIdJogador(),
                transferencia.getJogadorCompra().getNomeJogador(),

                transferencia.getClubeVendedor() != null
                        ? transferencia.getClubeVendedor().getIdClube()
                        : null,
                transferencia.getClubeVendedor() != null
                        ? transferencia.getClubeVendedor().getNomeClube()
                        : null,

                transferencia.getClubeComprador().getIdClube(),
                transferencia.getClubeComprador().getNomeClube(),

                transferencia.getStatusProposta()
        );
    }
}
